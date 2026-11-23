DESCRIPTION = "Eigen is a C++ template library for linear algebra: matrices, vectors, numerical solvers, and related algorithms."
HOMEPAGE = "http://eigen.tuxfamily.org/"
LICENSE = "Apache-2.0 AND BSD-3-Clause AND GPL-2.0-or-later AND MPL-2.0 AND Minpack"
# The GPL code is only the bench/btl benchmark suite and does not affect what is installed.
LICENSE:${PN} = "Apache-2.0 AND BSD-3-Clause AND MPL-2.0 AND Minpack"
LICENSE:${PN}-dbg = "Apache-2.0 AND BSD-3-Clause AND MPL-2.0 AND Minpack"
LICENSE:${PN}-dev = "Apache-2.0 AND BSD-3-Clause AND MPL-2.0 AND Minpack"
LIC_FILES_CHKSUM = "file://COPYING.MPL2;md5=48a3fe23ed1353e0995dadfda05ffdb6 \
                    file://COPYING.APACHE;md5=8de23b8e93c63005353056b2475e9aa5 \
                    file://COPYING.BSD;md5=2dd0510ee95e59ca28834b875bc96596 \
                    file://bench/btl/COPYING;md5=02023a9ab7f768bfdefdb98b629bb20d \
                    file://COPYING.MINPACK;md5=71d91b0f75ce79a75d3108a72bef8116 \
"

SRC_URI = "git://gitlab.com/libeigen/eigen.git;protocol=https;branch=5.0;tag=${PV}"

SRCREV = "bc3b39870ecb690a623a3f49149a358b95c5781d"

UPSTREAM_CHECK_GITTAGREGEX = "^(?P<pver>\d+(\.\d+)+)$"

inherit cmake

PACKAGECONFIG ??= ""

PACKAGECONFIG[blas] = "-DEIGEN_BUILD_BLAS=ON,-DEIGEN_BUILD_BLAS=OFF,libgfortran"
PACKAGECONFIG[lapack] = "-DEIGEN_BUILD_LAPACK=ON,-DEIGEN_BUILD_LAPACK=OFF,libgfortran"

# Empty pre-release/build strings keep the installed Eigen/Version at plain ${PV}
EXTRA_OECMAKE += " \
    -DEIGEN_BUILD_TESTING=OFF \
    -DEIGEN_BUILD_DOC=OFF \
    -DEIGEN_BUILD_DEMOS=OFF \
    -DEIGEN_PRERELEASE_VERSION= \
    -DEIGEN_BUILD_VERSION= \
"

FILES:${PN}-dev += "${datadir}/eigen3/cmake"

# ${PN} is empty so we need to tweak -dev and -dbg package dependencies
RDEPENDS:${PN}-dev = ""
RRECOMMENDS:${PN}-dbg = "${PN}-dev (= ${EXTENDPKGV})"

BBCLASSEXTEND = "native nativesdk"
