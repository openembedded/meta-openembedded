SUMMARY = "Evolution database backend server"
HOMEPAGE = "http://www.gnome.org/projects/evolution/"
BUGTRACKER = "https://bugzilla.gnome.org/"

LICENSE = "LGPLv2 & LGPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=6a6e689d19255cf0557f3fe7d7068212 \
                    file://src/camel/camel.h;endline=24;md5=342fc5e9357254bc30c24e43ae47d9a1 \
                    file://src/libedataserver/e-data-server-util.h;endline=20;md5=8f21a9c80ea82a4fb80b5f959f672543"

inherit gnomebase upstream-version-is-even

SRC_URI[archive.md5sum] = "30282c322622a5a2d4b4a26eee661af4"
SRC_URI[archive.sha256sum] = "39f83f1eee65c18785dfc2594720d5150e3fc37ea57e7b3b9bc2c40b4d3e4c0f"
PV = "3.36.4"

