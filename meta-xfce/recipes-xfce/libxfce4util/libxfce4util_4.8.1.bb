DESCRIPTION = "Basic utility library for Xfce4"
SECTION = "x11/libs"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=252890d9eee26aab7b432e8b8a616475"
DEPENDS = "intltool-native xfce4-dev-tools glib-2.0"

PR = "r0"

inherit xfce

EXTRA_OECONF += "--with-broken-putenv=yes"

SRC_URI[md5sum] = "2be3af4c7db5ad293a7525e1021e6f0f"
SRC_URI[sha256sum] = "732aeb4f845fb92efb5487e49662f5b8544ea5feb9f1e4bd7629e726aaa70c81"
