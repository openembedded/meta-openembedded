#!/usr/bin/env python3
"""
Test script to verify that python3-posix-ipc has message queue support.
This script should be run on the target system after the package is built and installed.
"""

def test_posix_ipc_messagequeue():
    """Test that MessageQueue is available in posix_ipc module"""
    try:
        import posix_ipc
        print("✓ posix_ipc module imported successfully")
        
        # Check if MessageQueue class exists
        if hasattr(posix_ipc, 'MessageQueue'):
            print("✓ MessageQueue class is available")
            
            # Try to create a test message queue
            try:
                # Use a unique name for testing
                test_queue_name = "/test_mq_12345"
                
                # Create a message queue (this will fail if no posix mq support)
                mq = posix_ipc.MessageQueue(test_queue_name, 
                                          posix_ipc.O_CREAT | posix_ipc.O_EXCL,
                                          mode=0o600, max_messages=10, max_message_size=256)
                print("✓ MessageQueue created successfully")
                
                # Send a test message
                mq.send("Hello, POSIX message queue!")
                print("✓ Message sent successfully")
                
                # Receive the test message
                message, priority = mq.receive()
                print(f"✓ Message received: {message.decode()}")
                
                # Clean up
                mq.close()
                mq.unlink()
                print("✓ MessageQueue cleaned up successfully")
                
                print("\n🎉 SUCCESS: python3-posix-ipc has working message queue support!")
                return True
                
            except posix_ipc.ExistentialError:
                print("⚠ Message queue already exists, trying to open existing one...")
                try:
                    mq = posix_ipc.MessageQueue(test_queue_name)
                    mq.close()
                    print("✓ Can open existing message queue")
                    return True
                except Exception as e:
                    print(f"✗ Error opening existing message queue: {e}")
                    return False
            except Exception as e:
                print(f"✗ Error creating/using MessageQueue: {e}")
                print("This might indicate the system doesn't support POSIX message queues")
                print("or the kernel module isn't loaded.")
                return False
        else:
            print("✗ MessageQueue class is NOT available")
            print("This indicates the package was built without message queue support")
            return False
            
    except ImportError as e:
        print(f"✗ Failed to import posix_ipc: {e}")
        return False
    except Exception as e:
        print(f"✗ Unexpected error: {e}")
        return False

def test_other_posix_ipc_features():
    """Test other POSIX IPC features to ensure they still work"""
    try:
        import posix_ipc
        
        # Test Semaphore
        if hasattr(posix_ipc, 'Semaphore'):
            print("✓ Semaphore class is available")
        else:
            print("✗ Semaphore class is missing")
            
        # Test SharedMemory  
        if hasattr(posix_ipc, 'SharedMemory'):
            print("✓ SharedMemory class is available")
        else:
            print("✗ SharedMemory class is missing")
            
    except Exception as e:
        print(f"✗ Error testing other features: {e}")

if __name__ == "__main__":
    print("Testing python3-posix-ipc message queue support...\n")
    
    success = test_posix_ipc_messagequeue()
    print()
    test_other_posix_ipc_features()
    
    if success:
        print("\n✅ Test PASSED: The fix appears to be working correctly!")
        exit(0)
    else:
        print("\n❌ Test FAILED: Message queue support is not working")
        exit(1)
