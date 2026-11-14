SUMMARY = "Simple C library for reading and writing PDF files"
HOMEPAGE = "https://www.msweet.org/pdfio"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57 \
                    file://NOTICE;md5=911592668bdcbb7554c90ec4cd2fb911"

DEPENDS = "zlib"

SRC_URI = "${GITHUB_BASE_URI}/download/v${PV}/${BP}.tar.gz"
SRC_URI[sha256sum] = "be9790ff2e9f0b7052fca8e96f2fdb6ebac903910e7ed8e2d1de4b1e55a40a23"

GITHUB_BASE_URI = "https://github.com/michaelrsweet/pdfio/releases"

inherit autotools-brokensep pkgconfig github-releases

PACKAGECONFIG ??= "libpng"
PACKAGECONFIG[libpng] = "--enable-libpng,--disable-libpng,libpng"

# The hand written Makefile links the shared library with DSOFLAGS only
EXTRA_OECONF = "--enable-shared --disable-static --with-dsoflags='${LDFLAGS}'"

do_install() {
    oe_runmake install BUILDROOT=${D}
}

BBCLASSEXTEND = "native nativesdk"
