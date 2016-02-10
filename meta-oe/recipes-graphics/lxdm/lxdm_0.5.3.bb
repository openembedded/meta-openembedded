SUMMARY = "LXDM is the lightweight display manager"
HOMEPAGE = "http://blog.lxde.org/?p=531"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"
SRC_URI = " \
    ${SOURCEFORGE_MIRROR}/project/${BPN}/${BPN}%20${PV}/${BPN}-${PV}.tar.xz \
    file://lxdm.conf \
    ${@base_contains('DISTRO_FEATURES', 'pam', 'file://lxdm-pam file://lxdm-pam-debug', '', d)} \
    ${@base_contains("DISTRO_TYPE", "debug", "", "file://0001-lxdm.conf.in-blacklist-root-for-release-images.patch",d)} \
    file://0002-let-autotools-create-lxdm.conf.patch \
"
SRC_URI[md5sum] = "061caae432634e6db38bbdc84bc6ffa0"
SRC_URI[sha256sum] = "4891efee81c72a400cc6703e40aa76f3f3853833d048b72ec805da0f93567f2f"

PE = "1"

DEPENDS = "virtual/libintl intltool-native cairo dbus gdk-pixbuf glib-2.0 gtk+ virtual/libx11 libxcb pango iso-codes"
DEPENDS += "${@base_contains("DISTRO_FEATURES", "systemd", "", "consolekit", d)}"

# combine oe-core way with angstrom DISTRO_TYPE
DISTRO_TYPE ?= "${@base_contains("IMAGE_FEATURES", "debug-tweaks", "debug", "",d)}"

inherit autotools pkgconfig gettext systemd distro_features_check
# depends on virtual/libx11
REQUIRED_DISTRO_FEATURES = "x11"

CFLAGS_append = " -fno-builtin-fork -fno-builtin-memset -fno-builtin-strstr "

EXTRA_OECONF += "--enable-gtk3=no --enable-password=yes --with-x -with-xconn=xcb \
    ${@base_contains('DISTRO_FEATURES', 'systemd', '--with-systemdsystemunitdir=${systemd_unitdir}/system/ --disable-consolekit', '--without-systemdsystemunitdir', d)} \
    ${@base_contains('DISTRO_FEATURES', 'pam', '--with-pam', '--without-pam', d)} \
"

do_configure_prepend() {
    cp ${STAGING_DATADIR}/gettext/po/Makefile.in.in ${S}/po/
}

do_compile_append() {
    # default background configured not available / no password field available / no default screensaver
    sed -i     -e 's,bg=,# bg=,g' \
        -e 's,# skip_password=,skip_password=,g' \
        -e 's,# arg=.*,arg=${bindir}/X -s 0,g' \
        ${S}/data/lxdm.conf.in
    # add default configuration
    oe_runmake -C ${B}/data lxdm.conf
}

do_install_append() {
    install -d ${D}${localstatedir}/lib/lxdm
    install -m 644 ${WORKDIR}/lxdm.conf ${D}${localstatedir}/lib/lxdm
    if ${@base_contains('DISTRO_FEATURES', 'pam', 'true', 'false', d)}; then
        # ArchLinux version of pam config has the following advantages:
        # * simple setup of passwordless login
        # * in XFCE powerdown/restart enabled in logoff dialog
        install -m 644 ${WORKDIR}/${@base_contains("DISTRO_TYPE", "debug", "lxdm-pam-debug", "lxdm-pam",d)} ${D}${sysconfdir}/pam.d/lxdm
    fi
}

# make installed languages choosable
pkg_postinst_${PN} () {
langs=""
for lang in `find $D${libdir}/locale -maxdepth 1 | grep _ | sort`; do
lang=`basename $lang`
if [ "x$langs" = "x" ]; then
    langs="$lang"
else
   langs="$langs $lang"
fi
done
sed -i "s:last_langs=.*$:last_langs=$langs:g" $D${localstatedir}/lib/lxdm/lxdm.conf
}

RDEPENDS_${PN} = "${@base_contains('DISTRO_FEATURES', 'pam', 'pam-plugin-loginuid', '', d)} setxkbmap bash librsvg-gtk"

RPROVIDES_${PN} += "${PN}-systemd"
RREPLACES_${PN} += "${PN}-systemd"
RCONFLICTS_${PN} += "${PN}-systemd"
SYSTEMD_SERVICE_${PN} = "lxdm.service"
