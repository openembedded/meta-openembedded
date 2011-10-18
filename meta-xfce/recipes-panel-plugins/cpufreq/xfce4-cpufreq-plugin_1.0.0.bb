DESCRIPTION = "Panel plugin to display frequency of all cpus"
HOMEPAGE = "http://goodies.xfce.org/projects/panel-plugins/xfce4-cpufreq-plugin"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=1f6f1c0be32491a0c8d2915607a28f36"

inherit xfce-panel-plugin

# SRC_URI must follow inherited one
SRC_URI += "file://port-to-libxfce4ui.patch"
SRC_URI[md5sum] = "24cae9b8583cae82b715b4f72aa8e341"
SRC_URI[sha256sum] = "e1b39a997487ef67d4f7863c81e2d9c1a700abded840ebdbe009c72214925460"
