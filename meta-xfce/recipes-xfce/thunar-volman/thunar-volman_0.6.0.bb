DESCRIPTION = "Automatic management of removable drives and media for thunar"
SECTION = "x11"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"
DEPENDS = "exo gtk+ libxfce4ui libxfce4util xfconf libnotify udev"
PR = "r1"

inherit xfce

SRC_URI[md5sum] = "2f166662dd100d5195da238af417f305"
SRC_URI[sha256sum] = "b1afd2b3b285cfcf07c11caa3d37fa5ff1fc06f7dc68fcca07f9a0d70daa694f"

RDEPENDS_${PN} = "eject"
