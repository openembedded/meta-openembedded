SUMMARY = "Panel plugin with graphical representation of the cpu frequency"
HOMEPAGE = "http://goodies.xfce.org/projects/panel-plugins/xfce4-eyes-plugin"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

inherit xfce-panel-plugin

SRC_URI[md5sum] = "86ae8bce96e028921fcc0ee8424ff565"
SRC_URI[sha256sum] = "4b9a335bcfdb8e4e2f6aedb3556575e5331f181912565f4289e354126230817c"

FILES_${PN} += "${datadir}/xfce4/eyes"
