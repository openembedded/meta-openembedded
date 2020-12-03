SUMMARY = "HTTP/2 C Library and tools"
HOMEPAGE = "https://nghttp2.org/"
SECTION = "libs"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=764abdf30b2eadd37ce47dcbce0ea1ec"
DEPENDS = "c-ares cunit jansson libev libevent libxml2 openssl zlib"

UPSTREAM_CHECK_URI = "https://github.com/nghttp2/nghttp2/releases"

SRC_URI = "\
    https://github.com/nghttp2/nghttp2/releases/download/v${PV}/nghttp2-${PV}.tar.xz \
    file://0001-fetch-ocsp-response-use-python3.patch \
"
SRC_URI[md5sum] = "c38f1d230af8cec480ff9dd60c9e0db0"
SRC_URI[sha256sum] = "c5a7f09020f31247d0d1609078a75efadeccb7e5b86fc2e4389189b1b431fe63"

inherit cmake manpages python3native
PACKAGECONFIG[manpages] = ""

# examples are never installed, and don't need to be built in the
# first place
EXTRA_OECMAKE = "-DENABLE_EXAMPLES=OFF -DENABLE_APP=ON"

do_install_append() {
	rm ${D}${bindir}/deflatehd ${D}${bindir}/inflatehd
}

PACKAGES =+ "lib${PN} ${PN}-client ${PN}-proxy ${PN}-server"

RDEPENDS_${PN} = "${PN}-client (>= ${PV}) ${PN}-proxy (>= ${PV}) ${PN}-server (>= ${PV})"
RDEPENDS_${PN}_class-native = ""
RDEPENDS_${PN}-proxy = "openssl python3-core python3-io python3-shell"

ALLOW_EMPTY_${PN} = "1"
FILES_${PN} = ""
FILES_lib${PN} = "${libdir}/*${SOLIBS}"
FILES_${PN}-client = "${bindir}/h2load ${bindir}/nghttp"
FILES_${PN}-proxy = "${bindir}/nghttpx ${datadir}/${BPN}/fetch-ocsp-response"
FILES_${PN}-server = "${bindir}/nghttpd"

BBCLASSEXTEND = "native"
