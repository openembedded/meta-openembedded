require llvm.inc
require llvm3.inc

# 3.2 is different then 2.8, 2.9 and 3.3
LIC_FILES_CHKSUM = "file://LICENSE.TXT;md5=60fdd7739841f04a2ce2171a726be8f3"

SRC_URI_append_libc-uclibc = " file://arm_fenv_uclibc.patch "
SRC_URI[md5sum] = "71610289bbc819e3e15fdd562809a2d7"
SRC_URI[sha256sum] = "125090c4d26740f1d5e9838477c931ed7d9ad70d599ba265f46f3a42cb066343"
