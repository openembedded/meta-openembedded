require recipes-graphics/xorg-lib/xorg-lib-common.inc
SUMMARY = "X Athena Widget Set"
DEPENDS += "xproto virtual/libx11 libxext xextproto libxt libxmu libxpm libxau xmlto-native"

LIC_FILES_CHKSUM = "file://COPYING;md5=1c65719d42900bb81b83e8293c20a364"

PE = "1"
PR = "r2"

SRC_URI[md5sum] = "f1ea52debce7a18cc26b21647a00ad8b"
SRC_URI[sha256sum] = "2d96bcf92638b8ec5c91d379f5ec2e7b15133adeb2ba22066d48bf3239ee1bdd"

do_install_append () {
    ln -sf libXaw6.so.6 ${D}${libdir}/libXaw.so.6
    ln -sf libXaw7.so.7 ${D}${libdir}/libXaw.so.7
    ln -sf libXaw7.so.7 ${D}${libdir}/libXaw.so
}

PACKAGES =+ "libxaw6 libxaw7 libxaw8"

FILES_libxaw6 = "${libdir}/libXaw*.so.6*"
FILES_libxaw7 = "${libdir}/libXaw*.so.7*"
FILES_libxaw8 = "${libdir}/libXaw8.so.8*"

# Avoid dependency on libxaw as it is not build
RDEPENDS_${PN}-dev = ""

XORG_PN = "libXaw"
