DESCRIPTION = "Thunar Archive Plugin allows you to create and extract archive files using file context menus in Thunar"
HOMEPAGE = "https://docs.xfce.org/xfce/thunar/archive"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=4cf66a4984120007c9881cc871cf49db"

inherit thunar-plugin

SRC_URI[sha256sum] = "cc0ffc86dc48e72edc6f6a61ad4345f99018526d5d854360960759ce1ec2ca22"

# install tap files in ${libdir}/thunar-archive-plugin
EXTRA_OECONF += "--libexecdir=${libdir}"
