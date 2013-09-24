DESCRIPTION = "A mode switching tool for controlling 'flip flop' (multiple device) USB gear"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

inherit autotools

DEPENDS = "libusb1"

SRC_URI = "http://www.draisberghof.de/usb_modeswitch/usb-modeswitch-${PV}.tar.bz2"
SRC_URI[md5sum] = "e48d4419d0574d342bb183f7465556d0"
SRC_URI[sha256sum] = "4706c9cfe825263e189f55730ea3d1d8634aeb15645a1098532e946e770f7f95"

EXTRA_OEMAKE = "TCL=${bindir}/tclsh"

FILES_${PN} = "${bindir} ${sysconfdir} ${base_libdir}/udev/usb_modeswitch ${sbindir} ${localstatedir}/lib/usb_modeswitch"
RDEPENDS_${PN} = "tcl"
RRECOMMENDS_${PN} = "usb-modeswitch-data"
