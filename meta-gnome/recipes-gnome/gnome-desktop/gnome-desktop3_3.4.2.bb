SUMMARY = "GNOME library for reading .desktop files"
SECTION = "x11/gnome"
LICENSE = "GPLv2 & LGPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
                    file://COPYING.LIB;md5=5f30f0716dfdd0d91eb439ebec522ec2"

BPN = "gnome-desktop"
GNOME_COMPRESS_TYPE ?= "xz"

inherit gnome pkgconfig
SRC_URI[archive.md5sum] = "efd11d3841c34cc1709d0ea1d3f83cf1"
SRC_URI[archive.sha256sum] = "7565e0a822132ea7b699cb8c64d42b57081ddefd0b5d911f4f4209be0c755952"

DEPENDS += "gsettings-desktop-schemas gconf libxrandr virtual/libx11 gtk+3 glib-2.0 gnome-doc-utils gnome-common startup-notification"

EXTRA_OECONF = "--disable-scrollkeeper --disable-desktop-docs"

do_configure_prepend () {
    cp ${STAGING_DATADIR}/gnome-common/data/omf.make ${S}
}

PACKAGES =+ "libgnome-desktop3"
FILES_libgnome-desktop3 = "${libdir}/lib*${SOLIBS} ${datadir}/libgnome-desktop*/pnp.ids ${datadir}/gnome/*xml"

RRECOMMENDS_libgnome-desktop3 += "gsettings-desktop-schemas"

