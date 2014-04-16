SUMMARY = "Evince is a document viewer for document formats like pdf, ps, djvu"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=96f2f8d5ee576a2163977938ea36fa7b"
SECTION = "x11/office"
DEPENDS = "gnome-icon-theme gnome-doc-utils-native libgnome-keyring tiff libxt ghostscript poppler libxml2 gtk+ gconf libglade"

PR = "r5"

inherit gnome pkgconfig gtk-icon-cache gsettings

SRC_URI += " \
    file://cross-compile-fix.patch \
    file://0001-tiff-fix-compile-warning.patch \
"

SRC_URI[archive.md5sum] = "ebc3ce6df8dcbf29cb9492f8dd031319"
SRC_URI[archive.sha256sum] = "2a4c91ae38f8b5028cebb91b9da9ddc50ea8ae3f3d429df89ba351da2d787ff7"

EXTRA_OECONF = " --enable-thumbnailer \
                 --disable-scrollkeeper \
                 --enable-pixbuf \
                 --disable-help \
"

do_install_append() {
    install -d install -d ${D}${datadir}/pixmaps
    install -m 0755 ${S}/data/icons/48x48/apps/evince.png ${D}${datadir}/pixmaps/
}

PACKAGECONFIG ??= ""
PACKAGECONFIG[nautilus] = "--enable-nautilus,--disable-nautilus,nautilus"

RDEPENDS_${PN} += "glib-2.0-utils"

PACKAGES =+ "${PN}-nautilus-extension"
FILES_${PN} += "${datadir}/dbus-1"
FILES_${PN}-dbg += "${libdir}/*/*/.debug \
                    ${libdir}/*/*/*/.debug"
FILES_${PN}-dev += "${libdir}/nautilus/extensions-2.0/*.la \
                    ${libdir}/evince/*/backends/*.la"
FILES_${PN}-staticdev += "${libdir}/nautilus/extensions-2.0/*.a \
                          ${libdir}/evince/*/backends/*.a"
FILES_${PN}-nautilus-extension = "${libdir}/nautilus/*/*so"
