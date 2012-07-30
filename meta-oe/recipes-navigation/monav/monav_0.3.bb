DESCRIPTION = "MoNav is a fast navigation system featuring exact routing with OpenStreetMap data."
HOMEPAGE = "http://code.google.com/p/monav"
SECTION = "x11/applications"
LICENSE = "GPLv3+"
LIC_FILES_CHKSUM = "file://misc/license_template_christian;md5=d99c9b3bafdde80adee296762376348d"

DEPENDS = "qt-mobility-x11"
PR = "r1"

SRC_URI = "http://monav.googlecode.com/files/${BPN}-${PV}.tar.gz \
           file://monav.png \
           file://monav.desktop"

SRC_URI[md5sum] = "d048ccef8c6a21e8656aa4af3fcb8329"
SRC_URI[sha256sum] = "5a3bf9e9f7368b81ba8e2f755960082fc42a2e2c78f9de645f99ba293c77ee7f"

inherit qmake2 qt4x11

EXTRA_QMAKEVARS_PRE="CONFIG+="release""
QMAKE_PROFILES="monavclient.pro"

do_install_append() {
    install -d ${D}${datadir}/monav
    install -d ${D}${datadir}/monav/images
    cp -a ${S}/images/* ${D}${datadir}/monav/images

    install -d ${D}${datadir}/icons
    install -m 0644 ${WORKDIR}/monav.png ${D}${datadir}/icons

    install -d ${D}${bindir}
    install -m 0755 ${S}/bin/monav ${D}${bindir}/monav

    install -d ${D}/${datadir}/applications
    install -m 0644 ${WORKDIR}/monav.desktop ${D}/${datadir}/applications
}

FILES_${PN} += "${bindir}/monav ${datadir}/icons/monav.png"
