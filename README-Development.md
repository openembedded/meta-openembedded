# Yocto Development Environment Setup Guide

## Overview
This guide documents the setup of a Yocto development environment in VSCode with WSL2 to work on the python3-posix-ipc MessageQueue fix.

## Environment Setup

### 1. VSCode Configuration
- **WSL Extension**: Installed and configured
- **Bitbake Extension**: Installed for syntax highlighting
- **Workspace**: `yocto-dev.code-workspace` configured with dual folders (Windows/WSL)

### 2. WSL2 Environment
- **Distribution**: Ubuntu 20.04 LTS
- **Python**: Upgraded to 3.9.23 (required for latest Yocto)
- **Location**: `/home/jpanov/yocto-workspace`

### 3. Yocto Build Environment
- **Poky**: Core Yocto build system
- **Meta-OpenEmbedded**: Contains python3-posix-ipc package
- **Build Directory**: `/home/jpanov/yocto-workspace/build`

## Project Structure
```
/home/jpanov/yocto-workspace/
├── poky/                           # Core Yocto build system
├── meta-openembedded/              # Meta-OpenEmbedded layers
│   └── meta-python/
│       └── recipes-devtools/python/
│           └── python3-posix-ipc_1.2.0.bb  # Fixed recipe
├── build/                          # Build directory
├── setup-build-env.sh             # Environment setup script
├── build-test.sh                   # Build and test script
└── test_posix_ipc_fix.py          # Runtime test script
```

## Key Files Modified

### 1. `python3-posix-ipc_1.2.0.bb`
**Fix Applied**: Added `LDFLAGS += "-lrt"` to ensure proper linking with librt during cross-compilation.

**Issue**: The prober script was failing to detect POSIX message queue support during cross-compilation because it wasn't linking with the real-time library.

**Solution**: Force linking with librt during the build process.

### 2. `test_posix_ipc_fix.py`
**Purpose**: Runtime verification script to confirm MessageQueue class is available and functional.

## Development Workflow

### Using VSCode
1. Open the workspace: `yocto-dev.code-workspace`
2. Terminal defaults to WSL Ubuntu
3. Edit files directly in VSCode (they sync between Windows and WSL)

### Building the Package
```bash
# Navigate to workspace in WSL
cd /home/jpanov/yocto-workspace

# Set up build environment (first time only)
./setup-build-env.sh

# Build the package
./build-test.sh
```

### Manual Build Process
```bash
# In WSL terminal
cd /home/jpanov/yocto-workspace
source poky/oe-init-build-env build

# Clean and build
bitbake -c clean python3-posix-ipc
bitbake python3-posix-ipc
```

### Testing the Fix
1. Deploy the built package to a target system
2. Run the test script:
   ```bash
   python3 test_posix_ipc_fix.py
   ```

## Troubleshooting

### Common Issues
1. **Python Version**: Ensure Python 3.9+ is installed in WSL
2. **Build Dependencies**: All Yocto dependencies installed via apt
3. **Layer Configuration**: Verify layers are properly added to bblayers.conf

### Build Locations
- **IPK Packages**: `build/tmp/deploy/ipk/`
- **Work Directory**: `build/tmp/work/`
- **Sysroot**: `build/tmp/sysroots/`

## Next Steps

1. **Build the Package**: Run `./build-test.sh` to build python3-posix-ipc
2. **Create Test Image**: Build a minimal image including the package
3. **Deploy and Test**: Run the test script on target hardware
4. **Submit PR**: If tests pass, submit the fix to meta-openembedded

## Quick Commands

```bash
# Check build status
bitbake -s python3-posix-ipc

# View recipe details
bitbake -e python3-posix-ipc | grep "^LDFLAGS"

# Build minimal test image
bitbake core-image-minimal

# Run QEMU for testing
runqemu qemux86-64
```

## Files for Reference
- **Recipe**: `meta-python/recipes-devtools/python/python3-posix-ipc_1.2.0.bb`
- **Test Script**: `test_posix_ipc_fix.py`
- **Build Scripts**: `setup-build-env.sh`, `build-test.sh`
- **Workspace**: `yocto-dev.code-workspace`
