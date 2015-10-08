SUMMARY = "GNOME bluetooth manager"
LICENSE = "GPLv2 & LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=eb723b61539feef013de476e68b5c50a \
                    file://COPYING.LIB;md5=a6f89e2100d9b6cdffcea4f398e37343 \
"

PR = "r1"

SECTION = "x11/gnome"
DEPENDS = "obexd gnome-doc-utils-native gnome-doc-utils gconf gtk+ dbus-glib libunique libnotify libgnome-keyring virtual/libx11 libxi intltool-native"
DEPENDS += "${@bb.utils.contains('DISTRO_FEATURES','bluez5','bluez5','bluez4',d)}"

inherit gnomebase gtk-icon-cache

SRC_URI += " \
    file://0001-bluetooth-input-Fix-compile-errors.patch \
    file://0002-bluetooth-client-use-valid-interface-names.patch \
    file://0003-Fix-build-with-libnotify-0.7.0.patch \
"
SRC_URI[archive.md5sum] = "f129686fe46c4c98eb70a0cc85d59cae"
SRC_URI[archive.sha256sum] = "57b1f06c96a1b85e1c19ff919d708cc38e95edae658881ed99968c325839a973"
GNOME_COMPRESS_TYPE="bz2"

# No 'nautilus-sendto' recipe in meta-gnome yet
EXTRA_OECONF += "--enable-nautilus-sendto=no"

# No native docbook XSL stylesheets recipe in OE yet
do_configure_prepend() {
    sed -i s/help// ${S}/Makefile.am
}

do_configure_append() {
    sed -i 's,func_fatal_error "error: cannot install,echo "bogus message about,' ${HOST_SYS}-libtool
}

RRECOMMENDS_${PN} += "obexd obex-data-server"

FILES_${PN}-dbg += "${libdir}/gnome-bluetooth/plugins/.debug/"
