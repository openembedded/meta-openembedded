DESCRIPTION = "Panel plugin displaying menu with quick access to folders, documents, and removable media"
HOMEPAGE = "http://goodies.xfce.org/projects/panel-plugins/xfce4-places-plugin"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b6952d9a47fc2ad0f315510e1290455f"

inherit xfce-panel-plugin

SRC_URI[md5sum] = "84c39fb123e07e1c7caaf006d9383656"
SRC_URI[sha256sum] = "6996051669a13d4791a5a453747801dc2f7f6fa5546785622d80b34966283d44"

PACKAGECONFIG ??= ""
PACKAGECONFIG[notify] = "--enable-notifications,--disable-notifications,libnotify"
