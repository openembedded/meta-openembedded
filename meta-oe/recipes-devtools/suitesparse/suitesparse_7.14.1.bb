SUMMARY = "Suite of sparse matrix algorithms"
HOMEPAGE = "https://people.engr.tamu.edu/davis/suitesparse.html"
LICENSE = "Apache-2.0 AND BSD-2-Clause AND BSD-3-Clause AND GPL-2.0-or-later AND GPL-3.0-only AND GPL-3.0-or-later AND LGPL-2.1-or-later AND (LGPL-3.0-or-later OR GPL-2.0-or-later)"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=9a78501f72366f5b3d08a496c9df9fee"

SRC_URI = "git://github.com/DrTimothyAldenDavis/SuiteSparse;protocol=https;branch=stable;tag=v${PV}"
SRCREV = "e202a5bcef55f5a780c26830ebd6ff13af193baf"

UPSTREAM_CHECK_GITTAGREGEX = "^v(?P<pver>\d+(\.\d+)+)$"

DEPENDS = "lapack gmp mpfr"

PROVIDES = "mongoose graphblas"
RPROVIDES:${PN} = "mongoose graphblas"

inherit cmake

EXTRA_OECMAKE = " \
    -DBLA_VENDOR=Generic \
    -DBUILD_STATIC_LIBS=OFF \
    -DCMAKE_SKIP_INSTALL_RPATH=ON \
    -DGRAPHBLAS_USE_JIT=OFF \
    -DSUITESPARSE_DEMOS=OFF \
    -DSUITESPARSE_USE_CUDA=OFF \
    -DSUITESPARSE_USE_FORTRAN=OFF \
    -DSUITESPARSE_USE_PYTHON=OFF \
"

SUITESPARSE_OPENMP_DEPS = ""
SUITESPARSE_OPENMP_DEPS:toolchain-clang = "openmp"

PACKAGECONFIG ??= "openmp"
PACKAGECONFIG[openmp] = "-DSUITESPARSE_USE_OPENMP=ON,-DSUITESPARSE_USE_OPENMP=OFF,${SUITESPARSE_OPENMP_DEPS}"

do_configure:append() {
    # GB_config.h embeds the compiler and flags for the JIT, which is disabled
    sed -i -e '/^#define GB_\(C_COMPILER\|C_FLAGS\|C_LINK_FLAGS\|OMP_INC\|OMP_INC_DIRS\|C_LIBRARIES\|CMAKE_LIBRARIES\) /s/".*"/""/' \
        ${S}/GraphBLAS/Config/GB_config.h
}

do_install:append() {
    # Drop recipe sysroot paths of dependencies from the CMake and pkg-config files
    sed -i -e 's#;${RECIPE_SYSROOT}${includedir}##g' -e 's#"${RECIPE_SYSROOT}${includedir};*#"#g' \
        ${D}${libdir}/cmake/*/*.cmake
    sed -i -e 's#${RECIPE_SYSROOT}${libdir}/lib\([^/ ]*\)\.so#-l\1#g' ${D}${libdir}/pkgconfig/*.pc
}

EXCLUDE_FROM_WORLD = "1"
