SUMMARY = "toml config parser and seriaizer for c++."
HOMEPAGE = "https://github.com/marzer/tomlplusplus"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=90960f22c10049c117d56ed2ee5ee167"

SRC_URI = "git://github.com/marzer/tomlplusplus.git;protocol=https;branch=master"

PV = "3.4.0"
SRCREV = "30172438cee64926dc41fdd9c11fb3ba5b2ba9de"
S = "${WORKDIR}/git"

DEPENDS = "cmake-native"

inherit meson
