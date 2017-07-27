DESCRIPTION = "Libraries providing API for reporting different problems in applications \
to different bug targets like Bugzilla, ftp, trac, etc..."
SUMMARY = "Generic library for reporting various problems"
HOMEPAGE = "https://abrt.readthedocs.org/"
LICENSE = "GPLv2+"
DEPENDS = "xmlrpc-c xmlrpc-c-native intltool-native \
        json-c libtar libnewt libproxy rpm \
        augeas satyr systemd gtk+3 \
"

LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"


SRC_URI = "https://github.com/abrt/${BPN}/archive/${PV}/${BPN}}-%{PV}.tar.gz"
SRC_URI += "file://0001-Makefile.am-remove-doc-and-apidoc.patch \
            file://0002-configure.ac-remove-prog-test-of-xmlto-and-asciidoc.patch \
            file://0003-without-build-plugins.patch \
            file://0004-configure.ac-remove-prog-test-of-augparse.patch \
            file://0005-remove-python2-support.patch \
"

SRC_URI[md5sum] = "f732a63dcc68cf96f0df02bcfc5fccc7"
SRC_URI[sha256sum] = "484e15759a079730608b708ea9ae74a045ebe74f35dbfe9c9c16834c4462bcd4"

inherit distro_features_check
REQUIRED_DISTRO_FEATURES = "systemd"

inherit gettext autotools python3native pkgconfig

RDEPENDS_${PN}-python3 += "${PN}"

do_patch[prefuncs] += "do_gen_version"
do_gen_version() {
    cd ${S}
    ./gen-version
}

PACKAGES += "python3-${PN}"

FILES_${PN} += "${datadir}/*"
FILES_${PN}-dbg += "${PYTHON_SITEPACKAGES_DIR}/*/.debug"
FILES_python3-${PN} = "${PYTHON_SITEPACKAGES_DIR}/*"

