DESCRIPTION = "A mode switching tool for controlling 'flip flop' (multiple device) USB gear"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

DEPENDS = "virtual/libusb0"

SRC_URI = "http://www.draisberghof.de/usb_modeswitch/usb-modeswitch-${PV}.tar.bz2 \
           "
SRC_URI[md5sum] = "76f6978f18cac41f269a346a5d0f1052"
SRC_URI[sha256sum] = "cb9794135e28e0a7984aab8e7bcc8c3f8a8f4aa9d2f024fa81b0b2a926fc0219"

do_install() {
	sed -i 's: -s : :g' Makefile
	oe_runmake install DESTDIR=${D}
}

FILES_${PN} = "${bindir} ${sysconfdir} ${base_libdir}/udev/usb_modeswitch ${sbindir}"
RRECOMMENDS_${PN} = "usb-modeswitch-data"
