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

SUMMARY = "Tools to enhance VMWare guest integration and performance"
HOMEPAGE = "https://github.com/vmware/open-vm-tools"
SECTION = "vmware-tools"

LICENSE = "LGPLv2.1 & GPLv2 & BSD & CDDLv1"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b66ba4cb4fc017682c95efc300410e79"
LICENSE_modules/freebsd/vmblock = "BSD"
LICENSE_modules/freebsd/vmmemctl = "GPLv2"
LICENSE_modules/freebsd/vmxnet = "GPLv2"
LICENSE_modules/linux = "GPLv2"
LICENSE_modules/solaris = "CDDLv1"

SRC_URI = "git://github.com/vmware/open-vm-tools.git;protocol=https \
           file://tools.conf \
           file://vmtoolsd.service \
           file://0001-configure.ac-don-t-use-dnet-config.patch \
           file://0002-add-include-sys-sysmacros.h.patch \
           "

SRCREV = "854c0bb374612f7e633b448ca273f970f154458b"

S = "${WORKDIR}/git/open-vm-tools"

DEPENDS = "glib-2.0 glib-2.0-native util-linux libdnet procps"

# open-vm-tools is supported only on x86.
COMPATIBLE_HOST = '(x86_64.*|i.86.*)-linux'

inherit autotools pkgconfig systemd

SYSTEMD_SERVICE_${PN} = "vmtoolsd.service"

EXTRA_OECONF = "--without-icu --disable-multimon --disable-docs \
                --disable-tests --without-gtkmm --without-xerces --without-pam \
                --disable-grabbitmqproxy --disable-vgauth --disable-deploypkg \
                --without-root-privileges --without-kernel-modules"

NO_X11_FLAGS = "--without-x --without-gtk2 --without-gtk3"
X11_DEPENDS = "libxext libxi libxrender libxrandr libxtst gtk+ gdk-pixbuf"
PACKAGECONFIG[x11] = ",${NO_X11_FLAGS},${X11_DEPENDS}"

# fuse gets implicitly detected; there is no --without-fuse option.
PACKAGECONFIG[fuse] = ",,fuse"

FILES_${PN} += "\
    ${libdir}/open-vm-tools/plugins/vmsvc/lib*.so \
    ${libdir}/open-vm-tools/plugins/common/lib*.so \
    ${sysconfdir}/vmware-tools/tools.conf \
    "
FILES_${PN}-locale += "${datadir}/open-vm-tools/messages"
FILES_${PN}-dev += "${libdir}/open-vm-tools/plugins/common/lib*.la"
FILES_${PN}-dbg += "\
    ${libdir}/open-vm-tools/plugins/common/.debug \
    ${libdir}/open-vm-tools/plugins/vmsvc/.debug \
    "

CONFFILES_${PN} += "${sysconfdir}/vmware-tools/tools.conf"

RDEPENDS_${PN} = "util-linux libdnet fuse"

do_install_append() {
    ln -sf ${sbindir}/mount.vmhgfs ${D}/sbin/mount.vmhgfs
    install -d ${D}${systemd_unitdir}/system ${D}${sysconfdir}/vmware-tools
    install -m 644 ${WORKDIR}/*.service ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/tools.conf ${D}${sysconfdir}/vmware-tools/tools.conf
}

do_configure_prepend() {
    export CUSTOM_PROCPS_NAME=procps
    export CUSTOM_PROCPS_LIBS=-L${STAGING_LIBDIR}/libprocps.so
    export CUSTOM_DNET_NAME=dnet
    export CUSTOM_DNET_LIBS=-L${STAGING_LIBDIR}/libdnet.so
}
