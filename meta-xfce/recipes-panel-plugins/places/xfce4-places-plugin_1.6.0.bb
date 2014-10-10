DESCRIPTION = "Panel plugin displaying menu with quick access to folders, documents, and removable media"
HOMEPAGE = "http://goodies.xfce.org/projects/panel-plugins/xfce4-places-plugin"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b6952d9a47fc2ad0f315510e1290455f"

inherit xfce-panel-plugin

SRC_URI[md5sum] = "8f3ec883efb0775052eeea816bbd8a23"
SRC_URI[sha256sum] = "07b044025a75f02b84e8848d6f81b0fcbb0cd9b8a4bf919cb7f0777b096b27a4"

PACKAGECONFIG ??= ""
PACKAGECONFIG[notify] = "--enable-notifications,--disable-notifications,libnotify"
