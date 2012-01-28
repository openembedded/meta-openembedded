DESCRIPTION = "Data files for usbmodeswitch"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

inherit allarch

SRC_URI = "http://www.draisberghof.de/usb_modeswitch/usb-modeswitch-data-${PV}.tar.bz2"
SRC_URI[md5sum] = "c899cc9c71bb35f3ec600b7cd1455c65"
SRC_URI[sha256sum] = "bce6b8cbbf293fe519584b63815fe7f665e8238e6e138151d8cf7cc85a7a7b0f"

do_install() {
	oe_runmake install DESTDIR=${D}
}

RDEPENDS_${PN} = "usb-modeswitch"
FILES_${PN} += "${base_libdir}/udev/rules.d/ \
                ${datadir}/usb_modeswitch"
