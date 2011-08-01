DESCRIPTION="Xfce4 Utilities"
SECTION = "x11"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=9ac2e7cff1ddaf48b6eab6028f23ef88"
DEPENDS = "gtk+ virtual/libx11 dbus libxfce4util libxfce4ui"
RDEPENDS_${PN} = "xrdb"

PR = "r0"

inherit xfce

# SRC_URI must follow inherited one
SRC_URI += " \
    file://relative-symlinks-docs.patch \
"

# usually we do not migrate (requires perl on target)
EXTRA_OECONF += "--disable-xfconf-migration"

FILES_${PN} += " \ 
	${libdir}/xfce4/xfconf-migration \
        ${datadir}/dbus-1/* \
        ${datadir}/xsessions/xfce.desktop \
"

SRC_URI[md5sum] = "7f48198f4bee3edf7869064c2922c609"
SRC_URI[sha256sum] = "a12b708b0cd19ffa07afb2ab2cd1bf9377ff7fbbfbe16d7493a5bb973bcb0aa5"
