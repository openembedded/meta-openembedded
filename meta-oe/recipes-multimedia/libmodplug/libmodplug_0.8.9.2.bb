SUMMARY = "Library for reading mod-like audio files"
HOMEPAGE = "http://modplug-xmms.sf.net"

LICENSE = "LicenseRef-PD"
LIC_FILES_CHKSUM = "file://COPYING;md5=c9182faa1f7c316f7b97d404bcbe3685"

SRC_URI = "${SOURCEFORGE_MIRROR}/modplug-xmms/libmodplug-${PV}.tar.gz"

SRC_URI[sha256sum] = "e4d9cb46da78a639d6eadb4ff3ab955f68c3099579b5310af64b37091e28957e"
UPSTREAM_CHECK_URI = "https://sourceforge.net/projects/modplug-xmms/files/libmodplug/"
UPSTREAM_CHECK_REGEX = "libmodplug-(?P<pver>\d+(\.\d+)+)\.tar"

inherit autotools pkgconfig

EXTRA_OECONF = "--disable-option-checking"

# NOTE: autotools_stage_all does nothing here, we need to do it manually
do_install:append() {
    install -d ${D}${includedir}/libmodplug
    install -m 0644 ${S}/src/modplug.h ${D}${includedir}/libmodplug
    install -m 0644 ${S}/src/modplug.h ${D}${includedir}/
}
