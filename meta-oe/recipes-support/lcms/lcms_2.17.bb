SUMMARY = "Little cms is a small-footprint, speed optimized color management engine"
SECTION = "libs"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e9ce323c4b71c943a785db90142b228a"

SRC_URI = "${SOURCEFORGE_MIRROR}/lcms/lcms2-${PV}.tar.gz"
SRC_URI[sha256sum] = "d11af569e42a1baa1650d20ad61d12e41af4fead4aa7964a01f93b08b53ab074"

DEPENDS = "tiff"

BBCLASSEXTEND = "native nativesdk"

S = "${UNPACKDIR}/lcms2-${PV}"

inherit autotools sourceforge-releases

CVE_PRODUCT += "littlecms:little_cms_color_engine"
