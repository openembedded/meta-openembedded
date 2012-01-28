DESCRIPTION = "A mode switching tool for controlling 'flip flop' (multiple device) USB gear"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

DEPENDS = "virtual/libusb0"

SRC_URI = "http://www.draisberghof.de/usb_modeswitch/usb-modeswitch-${PV}.tar.bz2 \
           file://0001-usbmodeswitch-leave-stripping-to-packager.patch \
           file://0002-usb-modeswitch-leave-script-munging-to-recipe.patch"
SRC_URI[md5sum] = "4f3d3b9342b59b488089a8a81abda3ae"
SRC_URI[sha256sum] = "b46b08b0899bbacfe9081ed907f8d0f3fac9e647c4d0f3705c85804cb3a431e0"

do_install() {
	sed 's_!/usr/bin_!'"${bindir}"'_' <usb_modeswitch.tcl >usb_modeswitch_dispatcher
	oe_runmake install DESTDIR=${D}
}

FILES_${PN} = "${bindir} ${sysconfdir} ${base_libdir}/udev/usb_modeswitch ${sbindir}"
RDEPENDS_${PN} = "tcl"
RRECOMMENDS_${PN} = "usb-modeswitch-data"
