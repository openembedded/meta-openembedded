SUMMARY = "Unicode line breaking and word breaking algorithms library"
DESCRIPTION = "libunibreak is an implementation of the line breaking and word \
breaking algorithms as described in Unicode Standard Annex 14 and 29."
HOMEPAGE = "https://github.com/adah1972/libunibreak"
SECTION = "libs"
LICENSE = "Zlib"
LIC_FILES_CHKSUM = "file://LICENCE;md5=4ed81deb6876b71d8c62fdb3e09e4776"

SRC_URI = "https://github.com/adah1972/libunibreak/releases/download/libunibreak_${@d.getVar('PV').replace('.', '_')}/libunibreak-${PV}.tar.gz"
SRC_URI[sha256sum] = "ac0dcc96c395bc86ff7f86f6b91d8b45bc8c39db775f3f1e73f947a11520737b"

UPSTREAM_CHECK_URI = "https://github.com/adah1972/libunibreak/releases"
UPSTREAM_CHECK_REGEX = "releases/tag/libunibreak_(?P<pver>\d+(_\d+)+)$"

inherit autotools pkgconfig
