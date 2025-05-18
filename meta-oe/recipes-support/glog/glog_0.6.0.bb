DESCRIPTION = "The glog library implements application-level logging. This \
library provides logging APIs based on C++-style streams and various helper \
macros."
HOMEPAGE = "https://github.com/google/glog"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://COPYING;md5=dc9db360e0bbd4e46672f3fd91dd6c4b"

SRC_URI = " \
    git://github.com/google/glog.git;nobranch=1;protocol=https \
    file://libexecinfo.patch \
    file://0001-Change-SleepForMilliseconds-parameter-from-unsigned-.patch \
"

SRCREV = "b33e3bad4c46c8a6345525fd822af355e5ef9446"

S = "${WORKDIR}/git"

inherit cmake

PACKAGECONFIG ?= "shared unwind 64bit-atomics"
PACKAGECONFIG:remove:riscv64 = "unwind"
PACKAGECONFIG:remove:riscv32 = "unwind 64bit-atomics"
PACKAGECONFIG:remove:mipsarch = "64bit-atomics"

PACKAGECONFIG:append:libc-musl:riscv64 = " execinfo"
PACKAGECONFIG:append:libc-musl:riscv32 = " execinfo"

PACKAGECONFIG[unwind] = "-DWITH_UNWIND=ON,-DWITH_UNWIND=OFF,libunwind,libunwind"
PACKAGECONFIG[execinfo] = ",,libexecinfo"
PACKAGECONFIG[shared] = "-DBUILD_SHARED_LIBS=ON,-DBUILD_SHARED_LIBS=OFF,,"
PACKAGECONFIG[64bit-atomics] = ",-DCMAKE_CXX_STANDARD_LIBRARIES='-latomic',,"

do_configure:append() {
    # remove WORKDIR info to improve reproducibility
    if [ -f  "${B}/config.h" ] ; then
        sed -i 's/'$(echo ${WORKDIR} | sed 's_/_\\/_g')'/../g' ${B}/config.h
    fi
}
