SUMMARY = "Common GNOME build files for C++ bindings"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"

inherit gnomebase

SRC_URI[archive.md5sum] = "9087b8612d75cbc1fad0c99e15c2a718"
SRC_URI[archive.sha256sum] = "b97d9b041e5952486cab620b44ab09f6013a478f43b6699ae899b8a4da189cd4"

GNOMEBASEBUILDCLASS = "meson"

BBCLASSEXTEND = "native"
