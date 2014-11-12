SUMMARY = "A partition editor to graphically manage disk partitions "
HOMEPAGE = "http://gparted.org/index.php"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

inherit autotools pkgconfig

SRC_URI = " \
    ${SOURCEFORGE_MIRROR}/project/${BPN}/${BPN}/${BPN}-${PV}/${BPN}-${PV}.tar.bz2 \
    file://0001-configure.ac-use-pkg-config-to-check-for-version-of-.patch \
"
SRC_URI[md5sum] = "d9df57f9d4b4dbd148644774f8bbccfb"
SRC_URI[sha256sum] = "9b34d73d4519352a7e7344c8403ef61253be1b4db3ff332c397b0b1eb5c1ddc2"

DEPENDS = "glib-2.0 gtkmm parted"

EXTRA_OECONF = "--disable-scrollkeeper --disable-doc"

FILES_${PN} += "${datadir}/icons ${datadir}/appdata"

RDEPENDS_${PN} = "dosfstools mtools e2fsprogs"
