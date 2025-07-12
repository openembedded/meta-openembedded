#!/bin/bash
# Yocto Build Environment Setup Script
# This script sets up the build environment for testing the python3-posix-ipc fix

set -e

WORKSPACE_DIR="/home/jpanov/yocto-workspace"
BUILD_DIR="$WORKSPACE_DIR/build"
POKY_DIR="$WORKSPACE_DIR/poky"
META_OE_DIR="$WORKSPACE_DIR/meta-openembedded"

echo "Setting up Yocto build environment..."

# Navigate to workspace
cd "$WORKSPACE_DIR"

# Source the build environment
echo "Sourcing build environment..."
source "$POKY_DIR/oe-init-build-env" "$BUILD_DIR"

# Add meta-openembedded layers to bblayers.conf
echo "Configuring layers..."
if ! grep -q "meta-oe" conf/bblayers.conf; then
    echo "Adding meta-openembedded layers..."
    bitbake-layers add-layer "$META_OE_DIR/meta-oe"
    bitbake-layers add-layer "$META_OE_DIR/meta-python"
fi

# Show layer configuration
echo "Current layers:"
bitbake-layers show-layers

echo "Build environment ready!"
echo "You can now build python3-posix-ipc with:"
echo "  bitbake python3-posix-ipc"
