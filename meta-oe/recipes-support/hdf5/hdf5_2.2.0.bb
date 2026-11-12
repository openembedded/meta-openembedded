SUMMARY = "Management suite for extremely large and complex data collections"
DESCRIPTION = "Unique technology suite that makes possible the management of \
extremely large and complex data collections"
HOMEPAGE = "https://www.hdfgroup.org/"
SECTION = "libs"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=71a191398102f76050a4e56e78cb4891"

inherit cmake pkgconfig siteinfo qemu multilib_header multilib_script

DEPENDS += "qemu-native zlib"

SRC_URI = "https://github.com/HDFGroup/hdf5/releases/download/${PV}/${BPN}-${PV}.tar.gz \
           file://0002-Remove-suffix-shared-from-shared-library-name.patch \
           file://0001-cmake-remove-build-flags.patch \
           file://CVE-2026-19025.patch \
           file://CVE-2026-19026-CVE-2026-19027-CVE-2026-19028.patch \
           file://CVE-2026-19023-CVE-2026-19024.patch \
           file://CVE-2026-120689.patch \
           file://CVE-2026-120754-CVE-2026-120755.patch \
           file://CVE-2026-120756.patch \
           file://CVE-2026-120757.patch \
           file://CVE-2026-120758.patch \
           "
SRC_URI[sha256sum] = "1a1ab8209b35586fbc1aa279ba76d102130b95badcb20ca329587219112d8c16"

UPSTREAM_CHECK_URI = "https://support.hdfgroup.org/releases/hdf5/"
UPSTREAM_CHECK_REGEX = "\./(?P<pver>\d+(\.\d+)+)/index\.html"

FILES:${PN} += "${libdir}/libhdf5.settings ${datadir}/*"

EXTRA_OECMAKE = " \
    -DHDF5_INSTALL_CMAKE_DIR=${libdir}/cmake \
    -DCMAKE_INSTALL_PREFIX='${prefix}' \
    -DHDF5_INSTALL_LIB_DIR='${baselib}' \
"

PACKAGECONFIG ??= ""
PACKAGECONFIG[cpp] = "-DHDF5_BUILD_CPP_LIB=ON,-DHDF5_BUILD_CPP_LIB=OFF"

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

do_configure:append() {
    sed -i -e 's|${WORKDIR}||g' ${B}/src/libhdf5.settings
    sed -i -e 's|${WORKDIR}||g' ${B}/src/H5build_settings.c
}

do_install:append() {
    # Used for generating config files on target
    oe_multilib_header H5pubconf.h
    sed -i -e 's|${RECIPE_SYSROOT_NATIVE}||g' ${D}${bindir}/h5cc
    if ${@bb.utils.contains('PACKAGECONFIG', 'cpp', 'true', 'false', d)}; then
        sed -i -e 's|${RECIPE_SYSROOT_NATIVE}||g' ${D}${bindir}/h5c++
    fi
}

MULTILIB_SCRIPTS += "${PN}:${bindir}/h5cc \
                     ${@bb.utils.contains('PACKAGECONFIG', 'cpp', '${PN}:${bindir}/h5c++', '', d)} \
"

BBCLASSEXTEND = "native"

# h5fuse.sh script needs bash
RDEPENDS:${PN} += "bash"
