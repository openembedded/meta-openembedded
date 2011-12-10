DESCRIPTION = "Gnome keyring library"
LICENSE = "LGPLv2 & GPLv2"
LIC_FILES_CHKSUM = "file://COPYING.GPL;md5=94d55d512a9ba36caa9b7df079bae19f \
                    file://COPYING;md5=0914b9d3ebaba41ef2e3e0ae16f296cf"

SECTION = "x11/gnome"

inherit gnome lib_package perlnative

SRC_URI[archive.md5sum] = "c42b2ca66204835d901d3dbfc1fa5ae6"
SRC_URI[archive.sha256sum] = "56388c0d81ddfdb57d30e4963c83ecc1c18498aab99395420e0fff69929a0f0c"

