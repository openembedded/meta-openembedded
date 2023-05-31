SUMMARY = "Evolution database backend server"
HOMEPAGE = "http://www.gnome.org/projects/evolution/"
BUGTRACKER = "https://bugzilla.gnome.org/"

LICENSE = "LGPL-2.0-only & LGPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=6a6e689d19255cf0557f3fe7d7068212 \
                    file://src/camel/camel.h;endline=24;md5=342fc5e9357254bc30c24e43ae47d9a1 \
                    file://src/libedataserver/e-data-server-util.h;endline=20;md5=8f21a9c80ea82a4fb80b5f959f672543"

inherit gnomebase upstream-version-is-even

SRC_URI[archive.sha256sum] = "1f3243df12b4f1d3298c9977d6221a6565fd279efc984e1ccf255245d04cffd5"
PV = "3.48.2"
