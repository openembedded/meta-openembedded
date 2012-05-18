DESCRIPTION="Xfce4 Application Finder"
SECTION = "x11"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"
DEPENDS="glib-2.0 gtk+ libxfce4util libxfce4ui garcon dbus-glib xfconf"

inherit xfce

SRC_URI[md5sum] = "799f70a9ad67b450da67810a5107e623"
SRC_URI[sha256sum] = "042aae7e366428a6a92b6353b3f2727d64823076a7752b8e0a15f1f8f3645439"
