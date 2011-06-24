DESCRIPTION = "GNOME library for reading .desktop files"
SECTION = "x11/gnome"
LICENSE = "GPLv2 & LGPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
                    file://COPYING.LIB;md5=5f30f0716dfdd0d91eb439ebec522ec2"

PR = "r1"

BPN = "gnome-desktop"

inherit gnome pkgconfig
SRC_URI[archive.md5sum] = "e2fd9d545d4d7ff66525f10364ab671e"
SRC_URI[archive.sha256sum] = "0ac9b4a358c6d92d20283cf56a9817262ff3ce038013cbb5d98e4ab97aa2c9a3"

DEPENDS += "gsettings-desktop-schemas gconf libxrandr virtual/libx11 gtk+3 glib-2.0 gnome-doc-utils"

EXTRA_OECONF = "--disable-scrollkeeper --disable-desktop-docs"

do_configure_prepend () {
    cp ${STAGING_DATADIR}/gnome-common/data/omf.make ${S}
}

PACKAGES =+ "libgnome-desktop3"
FILES_libgnome-desktop3 = "${libdir}/lib*${SOLIBS} ${datadir}/libgnome-desktop*/pnp.ids ${datadir}/gnome/*xml"

RRECOMMENDS_libgnome-desktop3 += "gsettings-desktop-schemas"

