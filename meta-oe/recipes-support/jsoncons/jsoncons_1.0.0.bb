DESCRIPTION = "A C++, header-only library for constructing JSON and JSON-like data formats."
HOMEPAGE = "https://github.com/danielaparker/jsoncons"

LICENSE = "BSL-1.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=6ee7f7ed2001e4cde4679fdb8926f820"

SRC_URI = "git://github.com/danielaparker/jsoncons.git;protocol=https;branch=master"
SRCREV = "853219aad6f5f049f9b7f6dcf2ff47db7990cdbd"

S = "${WORKDIR}/git"

inherit cmake

PACKAGECONFIG ??= ""
PACKAGECONFIG[tests] = "-DJSONCONS_BUILD_TESTS=ON,-DJSONCONS_BUILD_TESTS=OFF"
