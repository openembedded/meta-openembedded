DESCRIPTION = "Panel plugin displaying current CPU load, the memory in use, the swap space and the system uptime"
HOMEPAGE = "http://goodies.xfce.org/projects/panel-plugins/xfce4-systemload-plugin"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://COPYING;md5=9acb172a93ff6c43cce2aff790a8aef8"

inherit xfce-panel-plugin

DEPENDS += "upower"

SRC_URI[md5sum] = "f2c7e77c8ef7d654c0b429d2e109d4a0"
SRC_URI[sha256sum] = "4081d2c5ebda1c80d0eed48ae247a483ed4b15c8cb094fe009cb2304dda50cad"
