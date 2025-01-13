SUMMARY = "A high-performance REST toolkit written in C++"
HOMEPAGE = "pistacheio.github.io/pistache"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=fa818a259cbed7ce8bc2a22d35a464fc"

SRC_URI = "git://github.com/pistacheio/pistache.git;branch=master;protocol=https"
# SRCREV tagged 0.4.26
SRCREV = "ddffda861aa49012dcda28f1362d0339e718cd52"

S = "${WORKDIR}/git"

inherit pkgconfig meson

DEPENDS = "rapidjson"
DEPENDS += "${@bb.utils.contains("PACKAGECONFIG", "openssl", "openssl", "", d)}"
DEPENDS += "${@bb.utils.contains("PACKAGECONFIG", "brotli", "brotli", "", d)}"
DEPENDS += "${@bb.utils.contains("PACKAGECONFIG", "deflate", "zlib", "", d)}"
DEPENDS += "${@bb.utils.contains("PACKAGECONFIG", "zstd", "zstd", "", d)}"
DEPENDS += "${@bb.utils.contains("PACKAGECONFIG", "test", "gtest curl", "", d)}"

PACKAGECONFIG ?= "openssl brotli deflate zstd examples"
PACKAGECONFIG[openssl]  = "-DPISTACHE_USE_SSL=true, -DPISTACHE_USE_SSL=false"
PACKAGECONFIG[brotli]   = "-DPISTACHE_USE_CONTENT_ENCODING_BROTLI=true, -DPISTACHE_USE_CONTENT_ENCODING_BROTLI=false"
PACKAGECONFIG[deflate]  = "-DPISTACHE_USE_CONTENT_ENCODING_DEFLATE=true, -DPISTACHE_USE_CONTENT_ENCODING_DEFLATE=false"
PACKAGECONFIG[zstd]     = "-DPISTACHE_USE_CONTENT_ENCODING_ZSTD=true, -DPISTACHE_USE_CONTENT_ENCODING_ZSTD=false"
PACKAGECONFIG[examples] = "-DPISTACHE_BUILD_EXAMPLES=true, -DPISTACHE_BUILD_EXAMPLES=false"
PACKAGECONFIG[test]     = "-DPISTACHE_BUILD_TESTS=true, -DPISTACHE_BUILD_TESTS=false"

do_install:append () {
    if ${@bb.utils.contains("PACKAGECONFIG", "examples", "true", "false", d)}; then
        install -d ${D}${bindir}
        rm -rf ${B}/examples/*.p/
        install -m 0755 ${B}/examples/run*   ${D}${bindir}/
    fi
}

PACKAGES += "${PN}-examples"
FILES:${PN}:remove  = " ${bindir}/*"
RDEPENDS:${PN}-examples += "${PN}"
FILES:${PN}-examples = "${bindir}"

COMPATIBLE_HOST:libc-musl = "null"

BBCLASSEXTEND = "native nativesdk"
