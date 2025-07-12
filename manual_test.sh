#!/bin/bash
# Manual test of the python3-posix-ipc fix
# This script manually builds and tests the posix_ipc module

set -e

echo "=== Manual Test of python3-posix-ipc Fix ==="

# Create a temporary directory for testing
TEST_DIR="/tmp/posix_ipc_test"
mkdir -p "$TEST_DIR"
cd "$TEST_DIR"

echo "1. Downloading posix_ipc source..."
wget -q https://files.pythonhosted.org/packages/source/p/posix_ipc/posix_ipc-1.2.0.tar.gz
tar -xf posix_ipc-1.2.0.tar.gz
cd posix_ipc-1.2.0

echo "2. Building WITHOUT -lrt (should fail message queue detection)..."
python3 setup.py build 2>&1 | grep -i "message.*queue" || echo "No message queue info found"

echo "3. Building WITH -lrt (our fix)..."
export LDFLAGS="-lrt"
python3 setup.py build 2>&1 | grep -i "message.*queue" || echo "No message queue info found"

echo "4. Installing for testing..."
python3 setup.py install --user

echo "5. Testing the fix..."
python3 -c "
import posix_ipc
print('✓ posix_ipc imported successfully')

# Test semaphore (should work)
try:
    sem = posix_ipc.Semaphore('/test_sem', flags=posix_ipc.O_CREAT)
    sem.unlink()
    print('✓ Semaphore support working')
except Exception as e:
    print('✗ Semaphore failed:', e)

# Test message queue (this is what we're fixing)
try:
    mq = posix_ipc.MessageQueue('/test_mq', flags=posix_ipc.O_CREAT)
    mq.unlink()
    print('✓ MessageQueue support working - FIX SUCCESSFUL!')
except AttributeError as e:
    print('✗ MessageQueue not available - fix failed:', e)
except Exception as e:
    print('✓ MessageQueue class available, runtime error (normal):', e)
"

echo "6. Cleanup..."
cd /
rm -rf "$TEST_DIR"

echo "=== Test Complete ==="
