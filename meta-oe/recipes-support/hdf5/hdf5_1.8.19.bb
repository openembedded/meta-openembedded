SUMMARY = "Unique technology suite that makes possible the management of \
extremely large and complex data collections"
HOMEPAGE = "https://www.hdfgroup.org/"
SECTION = "libs"

LICENSE = "HDF5"
LIC_FILES_CHKSUM = "file://COPYING;md5=57e5351b17591e659eedae107265c606"

inherit cmake

SRC_URI = " \
    https://support.hdfgroup.org/ftp/HDF5/releases/hdf5-1.8/hdf5-${PV}/src/${BPN}-${PV}.tar.bz2 \
    file://H5lib_settings.c \
    file://H5Tinit.c \
    file://0001-cross-compiling-support.patch \
    file://0002-Remove-suffix-shared-from-shared-library-name.patch \
"

SRC_URI[md5sum] = "6f0353ee33e99089c110a1c8d2dd1b22"
SRC_URI[sha256sum] = "59c03816105d57990329537ad1049ba22c2b8afe1890085f0c022b75f1727238"

FILES_${PN} += "${libdir}/libhdf5.settings ${datadir}/*"

EXTRA_OECMAKE = " \
    -DTEST_LFS_WORKS_RUN=0 \
    -DTEST_LFS_WORKS_RUN__TRYRUN_OUTPUT=0 \
    -DH5_PRINTF_LL_TEST_RUN=1 \
    -DH5_PRINTF_LL_TEST_RUN__TRYRUN_OUTPUT='8' \
    -DH5_LDOUBLE_TO_LONG_SPECIAL_RUN=0 \
    -DH5_LDOUBLE_TO_LONG_SPECIAL_RUN__TRYRUN_OUTPUT= \
    -DH5_LONG_TO_LDOUBLE_SPECIAL_RUN=0 \
    -DH5_LONG_TO_LDOUBLE_SPECIAL_RUN__TRYRUN_OUTPUT= \
    -DH5_LDOUBLE_TO_LLONG_ACCURATE_RUN=0 \
    -DH5_LDOUBLE_TO_LLONG_ACCURATE_RUN__TRYRUN_OUTPUT= \
    -DH5_LLONG_TO_LDOUBLE_CORRECT_RUN=0 \
    -DH5_LLONG_TO_LDOUBLE_CORRECT_RUN__TRYRUN_OUTPUT= \
    -DH5_NO_ALIGNMENT_RESTRICTIONS_RUN=0 \
    -DH5_NO_ALIGNMENT_RESTRICTIONS_RUN__TRYRUN_OUTPUT= \
    -DCMAKE_INSTALL_PREFIX='${prefix}' \
"

do_unpack[postfuncs] += "gen_hd5file"
gen_hd5file() {
    install -m 544 ${WORKDIR}/H5lib_settings.c ${S}
    install -m 544 ${WORKDIR}/H5Tinit.c ${S}
}

do_install_append() {
    # Used for generating config files on target
    install -m 755 ${B}/bin/H5detect ${D}${bindir}
    install -m 755 ${B}/bin/H5make_libsettings ${D}${bindir}
}

BBCLASSEXTEND = "native"

SRC_DISTRIBUTE_LICENSES += "HDF5"
