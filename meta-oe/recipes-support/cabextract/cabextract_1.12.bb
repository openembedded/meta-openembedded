SUMMARY = "Software for extracting Microsoft cabinet files"
DESCRIPTION = "tool for extracting Microsoft cabinet files"
HOMEPAGE = "http://www.cabextract.org.uk/"
SECTION = "console/utils"

LICENSE = "GPL-3.0-or-later"
LIC_FILES_CHKSUM = "file://src/cabextract.c;beginline=4;endline=11;md5=b0a10c6d3843f262114e7ecf91fc7e78"

SRC_URI = "\
    https://www.cabextract.org.uk/cabextract-${PV}.tar.gz \
    file://fix-fnmatch.patch \
"

SRC_URI[sha256sum] = "8f39ad5f9c076b9c62c67dea4f8649e75dcc16899a2d8122ca32ba68262cb713"

UPSTREAM_CHECK_URI = "https://github.com/kyz/libmspack/tags"
UPSTREAM_CHECK_REGEX = "releases/tag/v(?P<pver>\d+(\.\d+)+)"

DEPENDS = "libmspack"

EXTRA_OECONF = "--with-external-libmspack"

inherit autotools pkgconfig

BBCLASSEXTEND += "native nativesdk"
