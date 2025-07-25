DESCRIPTION = "Ceres Solver is an open source C++ library for modeling and solving large, complicated optimization problems."
HOMEPAGE = "http://ceres-solver.org/"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3585a26c9cd9ec0cb36a7d65542878ca"

DEPENDS = "libeigen glog"

SRC_URI = "git://github.com/ceres-solver/ceres-solver.git;branch=master;protocol=https"
SRCREV = "85331393dc0dff09f6fb9903ab0c4bfa3e134b01"


inherit cmake

do_configure:prepend() {
    # otherwise https://github.com/ceres-solver/ceres-solver/blob/0b748597889f460764f6c980a00c6f502caa3875/cmake/AddGerritCommitHook.cmake#L68
    # will try to fetch https://ceres-solver-review.googlesource.com/tools/hooks/commit-msg durind do_configure
    # which sometimes gets stuck (as there is no TIMEOUT set in DOWNLOAD)
    # and we really don't need Gerrit's Change-Id tags when just building this
    touch ${S}/.git/hooks/commit-msg
}

# We don't want path to eigen3 in ceres-solver RSS to be
# used by components which use CeresConfig.cmake from their
# own RSS
# ceres-solver/1.14-r0/packages-split/ceres-solver-dev/usr/lib/cmake/Ceres/CeresConfig.cmake:  set(Eigen3_DIR ceres-solver/1.14-r0/recipe-sysroot/usr/share/eigen3/cmake)
# ceres-solver/1.14-r0/packages-split/ceres-solver-dev/usr/lib/cmake/Ceres/CeresConfig.cmake:  list(APPEND EIGEN_INCLUDE_DIR_HINTS ceres-solver/1.14-r0/recipe-sysroot/usr/include/eigen3)
# ceres-solver/1.14-r0/packages-split/ceres-solver-dev/usr/lib/cmake/Ceres/CeresConfig.cmake:    set(glog_DIR ceres-solver/1.14-r0/recipe-sysroot/usr/lib/cmake/glog)
SSTATE_SCAN_FILES += "*.cmake"

PACKAGECONFIG ??= ""

# suitesparse* recipes will be in meta-ros layer
PACKAGECONFIG[suitesparse] = "-DSUITESPARSE=ON,-DSUITESPARSE=OFF,suitesparse-config suitesparse-amd suitesparse-camd suitesparse-colamd suitesparse-ccolamd suitesparse-cholmod suitesparse-metis suitesparse-spqr"
PACKAGECONFIG[cxsparse] = "-DCXSPARSE=ON,-DCXSPARSE=OFF,suitesparse-cxsparse"
PACKAGECONFIG[lapack] = "-DLAPACK=ON,-DLAPACK=OFF,lapack"

# Only a static library and headers are created
RDEPENDS:${PN}-dev = ""
RRECOMMENDS:${PN}-dev = "${PN}-staticdev"
RRECOMMENDS:${PN}-dbg = "${PN}-dev (= ${EXTENDPKGV})"
