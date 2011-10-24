DESCRIPTION="Xfce4 Utilities"
SECTION = "x11"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=9ac2e7cff1ddaf48b6eab6028f23ef88"
DEPENDS = "gtk+ virtual/libx11 dbus libxfce4util libxfce4ui"

inherit xfce

# usually we do not migrate (requires perl on target)
EXTRA_OECONF += "--disable-xfconf-migration"

FILES_${PN} += " \ 
	${libdir}/xfce4/xfconf-migration \
        ${datadir}/dbus-1/* \
        ${datadir}/xsessions/xfce.desktop \
"
RDEPENDS_${PN} = "xrdb openssh-misc"

SRC_URI[md5sum] = "03b5fba5f63f4f1a31cc7d3728f3c87e"
SRC_URI[sha256sum] = "035070d7e608b29dbaeeb6ec541d8c2edbd28152d3ef111316c33871ab02b926"
