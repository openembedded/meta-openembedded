SUMMARY = "libmatroska is a C++ libary to parse Matroska files (.mkv and .mka)"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://LICENSE.LGPL;md5=f14599a2f089f6ff8c97e2baa4e3d575"

DEPENDS = "libebml"

SRC_URI = "http://dl.matroska.org/downloads/${BPN}/${BPN}-${PV}.tar.bz2"
SRC_URI[md5sum] = "f4a8c411f09d39c754eb726efd616043"
SRC_URI[sha256sum] = "5231ec958571365e719a6fd3c220227af6cf0f14e0bd1bcf766ddbc068d18e69"

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
