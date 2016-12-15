SUMMARY = "Apache Avro data serialization system."
HOMEPAGE = "http://apr.apache.org/"
SECTION = "libs"


LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=73bdf70f268f0b3b9c5a83dd7a6f3324"

DEPENDS = "jansson zlib xz"

SRC_URI = "${APACHE_MIRROR}/avro/avro-${PV}/c/avro-c-${PV}.tar.gz"

SRC_URI[md5sum] = "b268348536714541e10411823a1b59b0"
SRC_URI[sha256sum] = "e5042088fa47e1aa2860c5cfed0bd061d81f9e96628f8b4d87a24d9b8c5e4ecc"

LDFLAGS_append_libc-uclibc = " -lm"

inherit cmake
