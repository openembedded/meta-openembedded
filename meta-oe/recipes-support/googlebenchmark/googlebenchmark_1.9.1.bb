DESCRIPTION = "A microbenchmark support library"
HOMEPAGE = "https://github.com/google/benchmark"
SECTION = "libs"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "git://github.com/google/benchmark.git;protocol=https;branch=main"
SRCREV = "c58e6d0710581e3a08d65c349664128a8d9a2461"

S = "${WORKDIR}/git"

EXTRA_OECMAKE = " \
    -DBUILD_SHARED_LIBS=yes \
    -DBENCHMARK_ENABLE_TESTING=no \
    -DCMAKE_BUILD_TYPE=Release \
"

inherit cmake

FILES:${PN}-dev += "${libdir}/cmake"
