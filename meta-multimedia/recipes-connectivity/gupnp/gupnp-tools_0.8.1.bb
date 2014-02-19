SUMMARY = "Tools for GUPnP"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe \
                    file://src/network-light/main.c;beginline=1;endline=21;md5=033bf37c030780c5a72165846b3003f6"
DEPENDS = "gupnp gupnp-av gtk+ gnome-icon-theme"

SRC_URI = "http://download.gnome.org/sources/${BPN}/0.8/${BPN}-${PV}.tar.bz2"
SRC_URI[md5sum] = "cafc2a4f2d1a91a8c1228799073d1d7d"
SRC_URI[sha256sum] = "57d5ca899f8da7e4ae69e5f98d75d1603aa7f388022f9ccb357f90abb22b75b6"

inherit autotools pkgconfig
