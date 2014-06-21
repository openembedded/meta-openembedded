SUMMARY = "C++ bindings for the pango library"
SECTION = "libs"
LICENSE = "LGPLv2.1 & GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d8045f3b8f929c1cb29a1e3fd737b499 \
                    file://COPYING.tools;md5=751419260aa954499f7abaabaa882bbe"

DEPENDS = "mm-common cairomm glibmm pango"

SHRT_VER = "${@d.getVar('PV',1).split('.')[0]}.${@d.getVar('PV',1).split('.')[1]}"

SRC_URI = "ftp://ftp.gnome.org/pub/GNOME/sources/pangomm/${SHRT_VER}/pangomm-${PV}.tar.xz"
SRC_URI[md5sum] = "2c702caede167323c9ed9eed2b933098"
SRC_URI[sha256sum] = "0e82bbff62f626692a00f3772d8b17169a1842b8cc54d5f2ddb1fec2cede9e41"

inherit autotools pkgconfig

EXTRA_OECONF = " --disable-documentation "

FILES_${PN} = "${libdir}/lib*.so.*"
FILES_${PN}-dev += "${libdir}/*/include/ ${libdir}/pangomm-*/"

