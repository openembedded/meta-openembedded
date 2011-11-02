DESCRIPTION = "Panel plugin displaying instant disk/partition performance"
HOMEPAGE = "http://goodies.xfce.org/projects/panel-plugins/xfce4-diskperf-plugin"
LICENSE = "RogerSeguin"
LIC_FILES_CHKSUM = "file://COPYING;md5=d3e627798d6a60bece47aa8b3532e1f1"

inherit xfce-panel-plugin

# SRC_URI must follow inherited one
SRC_URI += "file://port-to-libxfce4ui.patch"
SRC_URI[md5sum] = "c6ece8123c762ee203cbc6fd5450b503"
SRC_URI[sha256sum] = "d75e2cd846c784cba804c5cc7796964b3965c764003ca4c1751c68ff83e85793"
