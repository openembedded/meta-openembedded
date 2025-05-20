SUMMARY = "A library for Serial/COM"
DESCRIPTION = "Async C/C++ I/O with COM/Serial Port Library."
HOMEPAGE = "https://github.com/thuanalg/libserialmodule"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=22cdd382a6275cb4c2e75c517952ac7c"
DEPENDS = "libsimplelog"
SRC_URI = "git://git@github.com/thuanalg/libserialmodule.git;branch=main;protocol=https;tag=v${PV}"
SRCREV = "975a95d38ba6bd4ae214567ed2bacd4b3e9f0268"
S = "${WORKDIR}/git"
inherit cmake
EXTRA_OECMAKE = "-DUNIX_LINUX=1 -DMETA_OPENEMBEDDED=1"

