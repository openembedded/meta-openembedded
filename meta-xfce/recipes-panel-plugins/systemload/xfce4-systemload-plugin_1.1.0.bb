DESCRIPTION = "Panel plugin displaying current CPU load, the memory in use, the swap space and the system uptime"
HOMEPAGE = "http://goodies.xfce.org/projects/panel-plugins/xfce4-systemload-plugin"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://COPYING;md5=9acb172a93ff6c43cce2aff790a8aef8"

inherit xfce-panel-plugin

DEPENDS += "upower"

SRC_URI[md5sum] = "130ad7befddd8705ca09a98e9cee5c3e"
SRC_URI[sha256sum] = "bc4ca8a7f66acb598d3f11bf0ee1fa650b869b3483176d708905d4ce9b1889f3"
