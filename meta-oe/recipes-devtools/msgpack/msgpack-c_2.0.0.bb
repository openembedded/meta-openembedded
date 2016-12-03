SUMMARY = "MessagePack implementation for C and C++"
DESCRIPTION = "MessagePack is an efficient binary serialization format. It's like JSON. but fast and small"
HOMEPAGE = "http://msgpack.org/index.html"
LICENSE = "BSL-1.0"
LIC_FILES_CHKSUM = "file://NOTICE;md5=7a858c074723608e08614061dc044352 \
                    file://COPYING;md5=0639c4209b6f2abf1437c813b208f2d3 \
                    file://LICENSE_1_0.txt;md5=e4224ccaecb14d942c71d31bef20d78c \
                   "
SRC_URI = "https://github.com/msgpack/msgpack-c/releases/download/cpp-${PV}/msgpack-${PV}.tar.gz"
SRC_URI[md5sum] = "55148cd856c72f954a6eb9cc889a7d2a"
SRC_URI[sha256sum] = "41de0989a3385061ab7307a1005655e780def6fc9c89af0ec942616aa787e136"

inherit cmake pkgconfig

S = "${WORKDIR}/msgpack-${PV}"
