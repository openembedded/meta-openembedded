SUMMARY = "Convex optimization package"
HOMEPAGE = "https://cvxopt.org"
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=878d3698da0459c16007d32b4b2915a4"

SRC_URI = "git://github.com/cvxopt/cvxopt;protocol=https;branch=master;tag=${PV}"

SRCREV = "add0181990cab7e018e82246028ded3d67778f2e"

UPSTREAM_CHECK_GITTAGREGEX = "^(?P<pver>\d+(\.\d+)+)$"

CVE_PRODUCT = "cvxopt"

RDEPENDS:${PN} += "lapack suitesparse"
DEPENDS += "lapack python3-setuptools-scm-native suitesparse"

inherit python_setuptools_build_meta

export CVXOPT_BLAS_LIB_DIR = "${STAGING_LIBDIR}"
export CVXOPT_SUITESPARSE_LIB_DIR = "${STAGING_LIBDIR}"
export CVXOPT_SUITESPARSE_INC_DIR = "${STAGING_INCDIR}"

EXCLUDE_FROM_WORLD = "1"
