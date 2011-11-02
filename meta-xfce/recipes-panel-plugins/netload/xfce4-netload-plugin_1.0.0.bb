DESCRIPTION = "Panel plugin displaying current load of the network interfaces"
HOMEPAGE = "http://goodies.xfce.org/projects/panel-plugins/xfce4-netload-plugin"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=35a7203c41b86d15546dddc05995f97f"

inherit xfce-panel-plugin

# SRC_URI must follow inherited one
SRC_URI += "file://port-to-libxfce4ui.patch"
SRC_URI[md5sum] = "cab53e8cc2b9bfdf7ffd2230916ca3df"
SRC_URI[sha256sum] = "f312d19fc6bfae525886a1f6e84e7d839f19a9f672be4e01f3df1c813f6a6032"
