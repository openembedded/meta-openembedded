# Instead of using -mfpu=vfp[2] we can use -mfpu=neon to make use of gcc intrinsics[1] and vectorize loops with -ftree-vectorize[3]
# [1] http://gcc.gnu.org/onlinedocs/gcc/ARM-NEON-Intrinsics.html
# [2] http://gcc.gnu.org/onlinedocs/gcc/ARM-Options.html 
# [3] https://support.codesourcery.com/GNUToolchain/kbentry29

# Can be 'softfp' or 'hardfp'
ARM_FP_MODE ?= "softfp"

ARM_FP_OPT = "${@['-mfloat-abi=softfp', '-mfloat-abi=hard'][bb.data.getVar('ARM_FP_MODE', d, 1) == 'hardfp']}"
ARM_FP_PACKAGESUFFIX = "${@['', '-hardfp'][bb.data.getVar('ARM_FP_MODE', d, 1) == 'hardfp']}"

TARGET_CC_ARCH = "-march=armv7-a -mtune=cortex-a8 -mfpu=neon ${ARM_FP_OPT}"

FEED_ARCH = "armv7a${ARM_FP_PACKAGESUFFIX}"
BASE_PACKAGE_ARCH = "armv7a${ARM_FP_PACKAGESUFFIX}"

PACKAGE_EXTRA_ARCHS += "armv4 armv4t armv5te armv6 armv7 armv7a${ARM_FP_PACKAGESUFFIX}"
