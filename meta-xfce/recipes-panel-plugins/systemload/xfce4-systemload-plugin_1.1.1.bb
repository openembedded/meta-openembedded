DESCRIPTION = "Panel plugin displaying current CPU load, the memory in use, the swap space and the system uptime"
HOMEPAGE = "http://goodies.xfce.org/projects/panel-plugins/xfce4-systemload-plugin"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://COPYING;md5=9acb172a93ff6c43cce2aff790a8aef8"

inherit xfce-panel-plugin

DEPENDS += "upower"

SRC_URI[md5sum] = "22d48c0883f9d598bce24e81d93e692e"
SRC_URI[sha256sum] = "1ac13efbe9e576c9effd5e0675574a57c8fd44cfee60d6e319eb4d03c1c8d9ae"
