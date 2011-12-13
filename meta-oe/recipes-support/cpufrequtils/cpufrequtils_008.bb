DESCRIPTION = "To make access to the Linux kernel cpufreq subsystem easier for users and cpufreq userspace tools, a cpufrequtils package was created"

DEPENDS = "libtool-cross"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

SRCREV = "adb7e044755aa06b12212d05c4acbcccb023d2cd"
# SRC_URI = "git://git.kernel.org/pub/scm/utils/kernel/cpufreq/cpufrequtils.git \

SRC_URI = "git://github.com/emagii/cpufrequtils.git \
           file://cpufrequtils-nls.patch \
          "

PR = "r2"

S = "${WORKDIR}/git"

TARGET_CC_ARCH += "${LDFLAGS}"

EXTRA_OEMAKE = "V=1 CROSS=${TARGET_PREFIX} LIBTOOL='${HOST_SYS}-libtool --tag cc' STRIPCMD=echo"

do_compile() {
        oe_runmake
}

do_install() {
        oe_runmake -e install DESTDIR=${D}
}

