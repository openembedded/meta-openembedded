SUMMARY = "Generic USB CCID smart card reader driver"
HOMEPAGE = "http://pcsclite.alioth.debian.org/ccid.html"
LICENSE = "LGPLv2.1+"
LIC_FILES_CHKSUM = "file://COPYING;md5=2d5025d4aa3495befef8f17206a5b0a1"

DEPENDS = "virtual/libusb0 pcsc-lite"
RDEPENDS_${PN} = "pcsc-lite"

SRC_URI = "https://alioth.debian.org/frs/download.php/file/3768/ccid-${PV}.tar.bz2 \
           file://no-dep-on-libfl.patch"

SRC_URI[md5sum] = "b6c37110f50b059a8ba94f118cfd679a"
SRC_URI[sha256sum] = "7aca09fa4b9099e423402fd5df424adbafa502888710d1fda6015b6c1ff637ef"

inherit autotools pkgconfig

FILES_${PN} += "${libdir}/pcsc/"
FILES_${PN}-dbg += "${libdir}/pcsc/drivers/*/*/*/.debug"
