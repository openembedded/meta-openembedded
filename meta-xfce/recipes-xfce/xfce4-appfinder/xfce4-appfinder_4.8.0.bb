DESCRIPTION="Xfce4 Application Finder"
SECTION = "x11"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"
DEPENDS="gtk+ libxfce4util libxfce4ui garcon xfconf"

PR = "r0"

inherit xfce

SRC_URI[md5sum] = "fb4797ef91b90d111b989e98c3e000e0"
SRC_URI[sha256sum] = "fd74184355e45f8a16cc3cdd32b012b6787a453deb3a8b96af90494c7a8ac77f"
