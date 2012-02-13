DESCRIPTION = "C++ bindings for the pango library."
SECTION = "libs"
LICENSE = "LGPLv2.1 & GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d8045f3b8f929c1cb29a1e3fd737b499 \
                    file://COPYING.tools;md5=751419260aa954499f7abaabaa882bbe"

PR = "r1"

DEPENDS = "mm-common cairomm glibmm"

SHRT_VER = "${@bb.data.getVar('PV',d,1).split('.')[0]}.${@bb.data.getVar('PV',d,1).split('.')[1]}"

SRC_URI = "ftp://ftp.gnome.org/pub/GNOME/sources/pangomm/${SHRT_VER}/pangomm-${PV}.tar.bz2"
SRC_URI[md5sum] = "005a474863495d3c6267429a80da6cf2"
SRC_URI[sha256sum] = "ec1d97245eada5cf18d0e7af3f6e31498f25623b4a354589d631d2dff92fd88f"

inherit autotools

EXTRA_OECONF = " --disable-documentation "

FILES_${PN} = "${libdir}/lib*.so.*"
FILES_${PN}-dev += "${libdir}/*/include/ ${libdir}/pangomm-*/"

