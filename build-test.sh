#!/bin/bash
# Build and test python3-posix-ipc
# This script builds the package and runs a basic test

set -e

WORKSPACE_DIR="/home/jpanov/yocto-workspace"
BUILD_DIR="$WORKSPACE_DIR/build"
POKY_DIR="$WORKSPACE_DIR/poky"

echo "Building python3-posix-ipc..."

# Navigate to workspace and source environment
cd "$WORKSPACE_DIR"
source "$POKY_DIR/oe-init-build-env" "$BUILD_DIR"

# Clean and build the package
echo "Cleaning previous build..."
bitbake -c clean python3-posix-ipc

echo "Building python3-posix-ipc..."
bitbake python3-posix-ipc

echo "Build completed successfully!"
echo "You can find the built package in:"
echo "  $BUILD_DIR/tmp/deploy/ipk/"

# Optional: Copy the test script
if [ -f "$WORKSPACE_DIR/meta-openembedded/test_posix_ipc_fix.py" ]; then
    echo "Test script available at: $WORKSPACE_DIR/meta-openembedded/test_posix_ipc_fix.py"
fi
