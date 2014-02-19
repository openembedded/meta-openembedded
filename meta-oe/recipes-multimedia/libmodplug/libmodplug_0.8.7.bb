SUMMARY = "Library for reading mod-like audio files"
HOMEPAGE = "http://modplug-xmms.sf.net"

LICENSE = "PD"
LIC_FILES_CHKSUM = "file://COPYING;md5=c9182faa1f7c316f7b97d404bcbe3685"

SRC_URI = "${SOURCEFORGE_MIRROR}/modplug-xmms/libmodplug-${PV}.tar.gz"
SRC_URI[md5sum] = "d2d9ccd8da22412999caed076140f786"
SRC_URI[sha256sum] = "3cfdebb60833a082e2f2b8faa3892bc9201d05c64051503e8007d8c98ae9e4c2"

inherit autotools pkgconfig

EXTRA_OECONF = "--disable-option-checking"

# NOTE: autotools_stage_all does nothing here, we need to do it manually
do_install_append() {
    install -d ${D}${includedir}/libmodplug
    install -m 0644 ${S}/src/modplug.h ${D}${includedir}/libmodplug
    install -m 0644 ${S}/src/modplug.h ${D}${includedir}/
}

