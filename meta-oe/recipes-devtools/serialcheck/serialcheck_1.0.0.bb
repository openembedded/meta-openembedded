SUMMARY = "Application to verify operation of serial ports"
HOMEPAGE = "http://git.breakpoint.cc/cgit/bigeasy/serialcheck.git/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI = "git://git.breakpoint.cc/bigeasy/serialcheck.git \
            file://0001-Add-option-to-enable-internal-loopback.patch \
            file://0002-Restore-original-loopback-config.patch"

SRCREV = "63854a2d0c0129efab132ec328a75279e013fb84"

S = "${WORKDIR}/git"

CFLAGS_prepend = "-Wall -Wextra -Wno-sign-compare -Wno-pointer-sign "

do_install() {
    install -d ${D}${bindir}
    install ${S}/serialcheck ${D}${bindir}
    install -d ${D}${docdir}/${BP}
    install ${S}/Readme.txt ${D}${docdir}/${BP}
}

BBCLASSEXTEND = "nativesdk"
