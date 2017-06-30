DESCRIPTION = "Open Asset Import Library is a portable Open Source library to import \
               various well-known 3D model formats in a uniform manner."
HOMEPAGE = "http://www.assimp.org/"
SECTION = "devel"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4cd8c0aedc7a0623476669377d7eeda8"

DEPENDS = "boost virtual/libgl"

SRCREV = "a8673d4828df5107186f49e5e4efa5316b727482"
SRC_URI = "git://github.com/assimp/assimp \
           file://0001-Fix-build-on-big-endian-architectures.patch \
           "
S = "${WORKDIR}/git"

inherit cmake

FILES_${PN}-dev += "${libdir}/cmake"
