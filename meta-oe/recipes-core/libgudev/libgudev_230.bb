SUMMARY = "GObject bindings for libudev."
DESCRIPTION = "This library provides GObject bindings for libudev. \
It was originally part of udev-extras, then udev, then systemd. It's now a project on its own."
HOMEPAGE = "https://wiki.gnome.org/Projects/libgudev"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

RCONFLICTS_${PN} = "systemd (<= 220)"

DEPENDS = "glib-2.0 udev"

inherit autotools pkgconfig

SRC_URI = "https://download.gnome.org/sources/${BPN}/${PV}/${BPN}-${PV}.tar.xz"
SRC_URI[md5sum] = "e4dee8f3f349e9372213d33887819a4d"
SRC_URI[sha256sum] = "a2e77faced0c66d7498403adefcc0707105e03db71a2b2abd620025b86347c18"
