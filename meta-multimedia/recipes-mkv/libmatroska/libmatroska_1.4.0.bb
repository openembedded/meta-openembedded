SUMMARY = "libmatroska is a C++ libary to parse Matroska files (.mkv and .mka)"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://LICENSE.LGPL;md5=f14599a2f089f6ff8c97e2baa4e3d575"

DEPENDS = "libebml"

SRC_URI = "http://dl.matroska.org/downloads/${BPN}/${BPN}-${PV}.tar.bz2"
SRC_URI[md5sum] = "5b83155019589b04a49f9db923b5bf74"
SRC_URI[sha256sum] = "1b6d02e75cdbfb6d282dcf2a902a259c3075404885d5e8063e6652a2b3f6c11b"

do_compile() {
    cd ${S}/make/linux
    oe_runmake CROSS="${TARGET_PREFIX}"
}

do_install() {
    cd ${S}/make/linux

    install -d ${D}${libdir}
    install -m 0644 libmatroska.a ${D}${libdir}
    install -m 0755 libmatroska.so.* ${D}${libdir}
    cp -a libmatroska.so ${D}${libdir}

    install -d ${D}${includedir}/matroska
    for i in ../../matroska/*.h; do
        install -m 0644 $i ${D}${includedir}/matroska
    done

    install -d ${D}${includedir}/matroska/c
    for i in ../../matroska/c/*.h; do
        install -m 0644 $i ${D}${includedir}/matroska/c
    done
}
