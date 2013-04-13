SUMMARY = "libebml is a C++ libary to parse EBML files."
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://LICENSE.LGPL;md5=f14599a2f089f6ff8c97e2baa4e3d575"

SRC_URI = "http://dl.matroska.org/downloads/libebml/libebml-${PV}.tar.bz2"
SRC_URI[md5sum] = "726cc2bd1a525929ff35ff9854c0ebab"
SRC_URI[sha256sum] = "476b08c6436a96c024a53e788e7c945ce9b41cd8654165763444aa7e5245b7a5"

do_compile() {
    cd ${S}/make/linux
    oe_runmake CROSS="${TARGET_PREFIX}"
}

do_install() {
    cd ${S}/make/linux

    install -d ${D}${libdir}
    install -m 0644 libebml.a ${D}${libdir}
    install -m 0755 libebml.so.* ${D}${libdir}
    cp -a libebml.so ${D}${libdir}

    install -d ${D}${includedir}/ebml
    for i in ../../ebml/*.h; do
        install -m 0644 $i ${D}${includedir}/ebml
    done

    install -d ${D}${includedir}/ebml/c
    for i in ../../ebml/c/*.h; do
        install -m 0644 $i ${D}${includedir}/ebml/c
    done
}
