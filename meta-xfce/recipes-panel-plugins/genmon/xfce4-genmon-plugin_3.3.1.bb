DESCRIPTION = "This plugin cyclically spawns the indicated script/program, captures its output (stdout) and displays the resulting string into the panel."
HOMEPAGE = "http://goodies.xfce.org/projects/panel-plugins/xfce4-genmon-plugin"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=68ad62c64cc6c620126241fd429e68fe"

inherit xfce-panel-plugin

SRC_URI += "file://port-to-libxfce4ui.patch"

SRC_URI[md5sum] = "e0022e15d4211a87c17d9f252b68e1d3"
SRC_URI[sha256sum] = "fe439d569f9ba3c8bdbfa907022a7c4a697ae4ae7fc5001a5600dfa5e1793471"
