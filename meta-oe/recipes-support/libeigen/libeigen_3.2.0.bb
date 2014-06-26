DESCRIPTION = "Eigen is a C++ template library for linear algebra: matrices, vectors, numerical solvers, and related algorithms."
AUTHOR = "Benoît Jacob and Gaël Guennebaud and others"
HOMEPAGE = "http://eigen.tuxfamily.org/"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://COPYING.MPL2;md5=815ca599c9df247a0c7f619bab123dad"

SRC_URI = "http://bitbucket.org/eigen/eigen/get/${PV}.tar.bz2 \
           file://eigen-disable-tests.patch"
SRC_URI[md5sum] = "894381be5be65bb7099c6fd91d61b357"
SRC_URI[sha256sum] = "011f78960d939227f9276173d0c4cc0053bb9e0a7356539a9a6c7c89d984fab6"

S = "${WORKDIR}/eigen-eigen-ffa86ffb5570"

inherit cmake

EXTRA_OECMAKE += "-Dpkg_config_libdir=${libdir}"

FILES_${PN} = "${includedir} ${libdir}"

# ${PN} is empty so we need to tweak -dev and -dbg package dependencies
RDEPENDS_${PN}-dev = ""
RRECOMMENDS_${PN}-dbg = "${PN}-dev (= ${EXTENDPKGV})"
