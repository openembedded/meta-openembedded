SUMMARY = "HTTP/2 C Library and tools"
HOMEPAGE = "https://nghttp2.org/"
SECTION = "libs"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=764abdf30b2eadd37ce47dcbce0ea1ec"

SRC_URI = "https://github.com/nghttp2/nghttp2/releases/download/v${PV}/nghttp2-${PV}.tar.xz"
SRC_URI[md5sum] = "f2ef3dd1e9fc6dc29fcdd4a465ebc020"
SRC_URI[sha256sum] = "e9bb86157b88eda5a6844a232e039febbb52c1aa44b640acbbfabe729b8287fc"

DEPENDS = "libxml2 openssl zlib jansson cunit c-ares"

inherit cmake pythonnative python-dir
