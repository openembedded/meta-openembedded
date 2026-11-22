SUMMARY = "udisks provides dbus interfaces for disks and storage devices"
LICENSE = "GPL-2.0-or-later AND LGPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=1c2da9358c6aacac4e4fbf1cb106cc1d"

DEPENDS = " \
    glib-2.0-native \
    libxslt-native \
    acl \
    libatasmart \
    polkit \
    libgudev \
    glib-2.0 \
    dbus-glib \
    libblockdev \
"
DEPENDS += "${@bb.utils.filter('DISTRO_FEATURES', 'systemd', d)}"

RDEPENDS:${PN} = "acl"

SRC_URI = " \
    git://github.com/storaged-project/udisks.git;branch=master;protocol=https \
    file://0001-Makefile.am-Dont-include-buildpath.patch \
"
SRCREV = "a0c93953d483f259164fb284d03691f11fe28009"

CVE_PRODUCT = "udisks"

inherit autotools-brokensep systemd gtk-doc gobject-introspection gettext features_check

REQUIRED_DISTRO_FEATURES = "polkit"

EXTRA_OECONF = "--disable-man --disable-gtk-doc"

do_configure:prepend() {
    # | configure.ac:656: error: required file 'build-aux/config.rpath' not found
    mkdir -p ${S}/build-aux
    touch ${S}/build-aux/config.rpath
}

PACKAGECONFIG ?= ""

PACKAGECONFIG[lvm2] = "--enable-lvm2,--disable-lvm2,lvm2"
PACKAGECONFIG[btrfs] = "--enable-btrfs,--disable-btrfs,,btrfs-tools"
PACKAGECONFIG[lsm] = "--enable-lsm,--disable-lsm,libstoragemgmt"

FILES:${PN} += " \
    ${datadir}/dbus-1/ \
    ${datadir}/polkit-1 \
    ${datadir}/bash-completion \
    ${datadir}/zsh \
    ${libdir}/polkit-1/extensions/*.so \
    ${nonarch_base_libdir}/udev/* \
    ${exec_prefix}${nonarch_base_libdir}/udisks2/* \
    ${systemd_system_unitdir} \
"

PACKAGES =+ "${PN}-libs"
FILES:${PN}-libs = "${libdir}/lib*${SOLIBS}"
FILES:${PN} += "${nonarch_libdir}/tmpfiles.d"

SYSTEMD_SERVICE:${PN} = "${BPN}.service"
SYSTEMD_AUTO_ENABLE = "disable"
