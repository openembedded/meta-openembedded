SUMMARY = "Evolution database backend server"
HOMEPAGE = "http://www.gnome.org/projects/evolution/"
BUGTRACKER = "https://bugzilla.gnome.org/"

LICENSE = "LGPL-2.0-only & LGPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=6a6e689d19255cf0557f3fe7d7068212 \
                    file://src/camel/camel.h;endline=24;md5=342fc5e9357254bc30c24e43ae47d9a1 \
                    file://src/libedataserver/e-data-server-util.h;endline=20;md5=8f21a9c80ea82a4fb80b5f959f672543"

GNOMEBASEBUILDCLASS = "cmake"
inherit gnomebase upstream-version-is-even

SRC_URI[archive.sha256sum] = "a54b47b977a97030f738a412f4f5d5b2efbef5e0ebe8929b7cce00ac54681084"
PV = "3.54.0"
