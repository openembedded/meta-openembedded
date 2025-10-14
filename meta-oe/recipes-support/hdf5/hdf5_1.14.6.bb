SUMMARY = "Management suite for extremely large and complex data collections"
DESCRIPTION = "Unique technology suite that makes possible the management of \
extremely large and complex data collections"
HOMEPAGE = "https://www.hdfgroup.org/"
SECTION = "libs"

LICENSE = "HDF5"
LIC_FILES_CHKSUM = "file://COPYING;md5=adebb1ecf1b3b80c13359e18ef67301e"

inherit cmake siteinfo qemu multilib_header multilib_script

DEPENDS += "qemu-native zlib"

SRC_URI = "https://support.hdfgroup.org/releases/hdf5/v1_14/v1_14_6/downloads/${BPN}-${PV}.tar.gz \
           file://0002-Remove-suffix-shared-from-shared-library-name.patch \
           file://0001-cmake-remove-build-flags.patch \
           file://0001-Fix-CVE-2025-2153-5795.patch \
           file://0001-Fix-CVE-2025-2310-5872.patch \
           file://0001-Refix-of-the-attempts-in-PR-5209-5722.patch \
           file://0001-Fix-CVE-2025-2924-5814.patch \
           file://0001-Fix-CVE-2025-2925-5739.patch \
           file://0001-Fixes-CVE-2025-6750-5856.patch \
           "
SRC_URI[sha256sum] = "e4defbac30f50d64e1556374aa49e574417c9e72c6b1de7a4ff88c4b1bea6e9b"

FILES:${PN} += "${libdir}/libhdf5.settings ${datadir}/*"

EXTRA_OECMAKE = " \
    -DHDF5_INSTALL_CMAKE_DIR=${libdir}/cmake \
    -DCMAKE_INSTALL_PREFIX='${prefix}' \
    -DHDF5_INSTALL_LIB_DIR='${baselib}' \
"
EXTRA_OECMAKE:prepend:class-target = "-DCMAKE_CROSSCOMPILING_EMULATOR=${WORKDIR}/qemuwrapper "

gen_emu() {
        # Write out a qemu wrapper that will be used by cmake
        # so that it can run target helper binaries through that.
        qemu_binary="${@qemu_wrapper_cmdline(d, d.getVar('STAGING_DIR_HOST'), [d.expand('${STAGING_DIR_HOST}${libdir}'),d.expand('${STAGING_DIR_HOST}${base_libdir}')])}"
        cat > ${WORKDIR}/qemuwrapper << EOF
#!/bin/sh
$qemu_binary "\$@"
EOF
        chmod +x ${WORKDIR}/qemuwrapper
}
gen_emu:class-native = ""

do_unpack[postfuncs] += "gen_emu"

MULTILIB_SCRIPTS += "${PN}:${bindir}/h5cc \
                     ${PN}:${bindir}/h5hlcc \
"

do_configure:append() {
    sed -i -e 's|${WORKDIR}||g' ${B}/src/libhdf5.settings
    sed -i -e 's|${WORKDIR}||g' ${B}/src/H5build_settings.c
}

do_install:append() {
    # Used for generating config files on target
    oe_multilib_header H5pubconf.h
    # remove the buildpath
    sed -i -e 's|${RECIPE_SYSROOT}||g' ${D}${libdir}/pkgconfig/hdf5.pc
    sed -i -e 's|${RECIPE_SYSROOT}||g' ${D}${libdir}/cmake/hdf5-targets.cmake
    sed -i -e 's|${RECIPE_SYSROOT_NATIVE}||g' ${D}${bindir}/h5hlcc
    sed -i -e 's|${RECIPE_SYSROOT_NATIVE}||g' ${D}${bindir}/h5cc
}

BBCLASSEXTEND = "native"

# h5fuse.sh script needs bash
RDEPENDS:${PN} += "bash"
