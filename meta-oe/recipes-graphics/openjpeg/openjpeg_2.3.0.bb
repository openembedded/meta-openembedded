DESCRIPTION = "OpenJPEG library is an open-source JPEG 2000 codec"
HOMEPAGE = "http://www.openjpeg.org"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c648878b4840d7babaade1303e7f108c"

DEPENDS = "libpng tiff lcms zlib"

SRC_URI = "git://github.com/uclouvain/openjpeg.git"
SRCREV = "081de4b15f54cb4482035b7bf5e3fb443e4bc84b"
S = "${WORKDIR}/git"

inherit cmake

# standard path for *.cmake
EXTRA_OECMAKE += "-DOPENJPEG_INSTALL_PACKAGE_DIR=${baselib}/cmake \
                  -DOPENJPEG_INSTALL_LIB_DIR:PATH=${libdir}"
