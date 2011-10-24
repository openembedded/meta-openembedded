DESCRIPTION = "Subsurface is an open source dive log program."
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://gpl-2.0.txt;md5=b234ee4d69f5fce4486a80fdaf4a4263"

DEPENDS = "libdivecomputer gtk+ libxml2 glib-2.0"

inherit gtk-icon-cache

inherit gitpkgv
PKGV = "${GITPKGVTAG}"
PV = "1.1"

SRCREV = "bd275d73ac06823619230915a3aa29deddc996fb"
SRC_URI = "git://subsurface.hohndel.org/subsurface.git"
S = "${WORKDIR}/git"

EXTRA_OEMAKE = "CC='${CC}' \
                libdc-usr=${STAGING_INCDIR}/libdivecomputer/* \
                LIBDIVECOMPUTERINCLUDES=${STAGING_INCDIR}/libdivecomputer \
                LIBDIVECOMPUTERARCHIVE=${STAGING_LIBDIR}/libdivecomputer.a \
               "

do_install() {
	oe_runmake install DESTDIR=${D}
	rm ${D}${datadir}/icons/hicolor/icon-theme.cache
}

FILES_${PN} += "${datadir}/icons/hicolor/scalable/apps/subsurface.svg"
RRECOMMENDS_${PN}_append_libc-glibc = " eglibc-gconv-iso8859-15"

