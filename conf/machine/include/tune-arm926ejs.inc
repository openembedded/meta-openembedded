FEED_ARCH = "armv5te"
PACKAGE_EXTRA_ARCHS += "armv4 armv4t armv5te"
# For gcc 3.x you need:
#TARGET_CC_ARCH = "-march=armv5te -mtune=arm926ejs"
# For gcc 4.x you need:
TARGET_CC_ARCH = "-march=armv5te -mtune=arm926ej-s"
BASE_PACKAGE_ARCH = "armv5te"
