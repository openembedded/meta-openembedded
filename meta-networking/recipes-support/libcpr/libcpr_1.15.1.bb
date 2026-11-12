SUMMARY = "Curl for People - C++ Requests"
DESCRIPTION = "Curl for People C++ Requests is a simple wrapper around \
    libcurl inspired by the excellent Python Requests project."
HOMEPAGE = "https://docs.libcpr.org/"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=08beaae5deae1c43c065592da8f38095"

DEPENDS = "curl openssl"

SRC_URI = "git://github.com/libcpr/cpr.git;protocol=https;nobranch=1;tag=${PV}"
SRCREV = "3139b2682f0b1d6af8c7ae48cf400f9d9988deec"


inherit cmake

# building tests is currently using FetchContent for mongoose
EXTRA_OECMAKE += "\
    -DCPR_USE_SYSTEM_CURL=ON \
    -DCPR_BUILD_TESTS=OFF \
"

BBCLASSEXTEND = "native nativesdk"
