SUMMARY = "A C++11 library for serialization"
HOMEPAGE = "https://uscilab.github.io/cereal/"

SECTION = "libs"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e612690af2f575dfd02e2e91443cea23"

SRCREV = "64f50dbd5cecdaba785217e2b0aeea3a4f1cdfab"
SRC_URI = "git://github.com/USCiLab/cereal.git"

S = "${WORKDIR}/git"

inherit cmake

EXTRA_OECMAKE = "-DJUST_INSTALL_CEREAL=ON"

ALLOW_EMPTY_${PN} = "1"

BBCLASSEXTEND = "native nativesdk"
