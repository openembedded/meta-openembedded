# This recipe is modified from the recipe originally found in the Open-Switch
# repository:
#
# https://github.com/open-switch/ops-build
# yocto/openswitch/meta-foss-openswitch/meta-oe/recipes-support/open-vm-tools/open-vm-tools_10.0.5.bb
# Commit 9008de2d8e100f3f868c66765742bca9fa98f3f9
#
# The recipe packaging has been relicensed under the MIT license for inclusion
# in meta-openembedded by agreement of the author (Diego Dompe).
#

DECRIPTION = "open-vm-tools"
SECTION = "vmware-tools"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=5804fe91d3294da4ac47c02b454bbc8a"

PR = "r5"

SRC_URI = "git://github.com/vmware/open-vm-tools.git;protocol=https \
           file://tools.conf \
           file://vmtoolsd.service \
           file://0001-Fix-kernel-detection.patch \
           "

SRCREV = "538ea037a1549b6fd4e57529f7448a3fd2aa47af"

S = "${WORKDIR}/git/open-vm-tools"

DEPENDS = "virtual/kernel glib-2.0 util-linux libdnet procps libmspack"
RDEPENDS_${PN} = "util-linux libdnet kernel-module-vmhgfs"

inherit module-base kernel-module-split autotools pkgconfig systemd

# from module.bbclass...
addtask make_scripts after do_patch before do_compile
do_make_scripts[lockfiles] = "${TMPDIR}/kernel-scripts.lock"
do_make_scripts[depends] = "virtual/kernel:do_shared_workdir"
# add all splitted modules to PN RDEPENDS, PN can be empty now
KERNEL_MODULES_META_PACKAGE = "${PN}"

SYSTEMD_SERVICE_${PN} = "vmtoolsd.service"

EXTRA_OECONF = "--without-icu --disable-multimon --disable-docs --disable-tests \
		--without-gtk2 --without-gtkmm --without-xerces --without-pam \
                --disable-grabbitmqproxy --disable-vgauth --disable-deploypkg \
		--with-linuxdir=${STAGING_KERNEL_DIR} --with-kernel-release=${KERNEL_VERSION} --without-root-privileges"

EXTRA_OECONF += "${@base_contains('DISTRO_FEATURES', 'x11', '', '--without-x', d)}"

EXTRA_OEMAKE = "KERNEL_RELEASE=${KERNEL_VERSION}"


CFLAGS += '-Wno-error=deprecated-declarations'

FILES_${PN} += "/usr/lib/open-vm-tools/plugins/vmsvc/lib*.so \
		/usr/lib/open-vm-tools/plugins/common/lib*.so \
    ${sysconfdir}/vmware-tools/tools.conf"
FILES_${PN}-locale += "/usr/share/open-vm-tools/messages"
FILES_${PN}-dev += "/usr/lib/open-vm-tools/plugins/common/lib*.la"
FILES_${PN}-dbg += "/usr/lib/open-vm-tools/plugins/common/.debug \
		    /usr/lib/open-vm-tools/plugins/vmsvc/.debug"

CONFFILES_${PN} += "${sysconfdir}/vmware-tools/tools.conf"

do_install_append() {
    ln -sf /usr/sbin/mount.vmhgfs ${D}/sbin/mount.vmhgfs
    install -d ${D}${systemd_unitdir}/system ${D}${sysconfdir}/vmware-tools
    install -m 644 ${WORKDIR}/*.service ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/tools.conf ${D}${sysconfdir}/vmware-tools/tools.conf
}

do_configure_prepend() {
    export CUSTOM_PROCPS_NAME=procps
    export CUSTOM_PROCPS_LIBS=-L${STAGING_LIBDIR}/libprocps.so
}
