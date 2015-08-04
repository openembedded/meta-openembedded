SUMMARY = "A partition editor to graphically manage disk partitions "
HOMEPAGE = "http://gparted.org/index.php"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

inherit autotools pkgconfig

SRC_URI = "${SOURCEFORGE_MIRROR}/project/${BPN}/${BPN}/${BPN}-${PV}/${BPN}-${PV}.tar.bz2"
SRC_URI[md5sum] = "5901dca33ced32213cccb13a2713dea8"
SRC_URI[sha256sum] = "7fd1dddf29814c2982f55ab765b8918561e1b5dabf792012c66b4d3ba25da754"

DEPENDS = "glib-2.0 gtkmm parted"

EXTRA_OECONF = "--disable-scrollkeeper --disable-doc"

FILES_${PN} += "${datadir}/icons ${datadir}/appdata"

RDEPENDS_${PN} = "dosfstools mtools e2fsprogs"
