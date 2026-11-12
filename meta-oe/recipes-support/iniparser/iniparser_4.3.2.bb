SUMMARY = "The iniParser library is a simple C library offering INI file parsing services (both reading and writing)."
SECTION = "libs"
HOMEPAGE = "https://gitlab.com/iniparser/iniparser"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8474d3b745f77e203f1fc82fb0bb7678"
SRCBRANCH = "main"
SRCREV = "4fff3f22b63819d30ad5c2e4d7c7df9ec272cffc"

SRC_URI = "git://gitlab.com/iniparser/iniparser.git;protocol=https;branch=${SRCBRANCH};lfs=0"

inherit cmake

EXTRA_OECMAKE = " \
	-DBUILD_DOCS=OFF \
	"

FILES_${PN}-staticdev += "${libdir}/cmake/iniparser/iniparser-staticTargets*.cmake"

CVE_STATUS[CVE-2025-0633] = "fixed-version: fixed in v4.2.6"
