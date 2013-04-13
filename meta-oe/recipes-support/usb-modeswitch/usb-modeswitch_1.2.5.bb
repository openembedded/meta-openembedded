DESCRIPTION = "A mode switching tool for controlling 'flip flop' (multiple device) USB gear"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

DEPENDS = "virtual/libusb0"

SRC_URI = "http://www.draisberghof.de/usb_modeswitch/usb-modeswitch-${PV}.tar.bz2 \
           file://0001-usbmodeswitch-leave-stripping-to-packager.patch \
           file://0002-usb-modeswitch-leave-script-munging-to-recipe.patch"
SRC_URI[md5sum] = "c393603908eceab95444c5bde790f6f0"
SRC_URI[sha256sum] = "ce47a3dec3e4c93e0a2fcea64278d0e289e6e78d8e1381c54f03986e443ab90f"

do_install() {
    sed 's_!/usr/bin_!'"${bindir}"'_' <usb_modeswitch.tcl >usb_modeswitch_dispatcher
    oe_runmake install DESTDIR=${D}
}

FILES_${PN} = "${bindir} ${sysconfdir} ${base_libdir}/udev/usb_modeswitch ${sbindir} ${localstatedir}/lib/usb_modeswitch"
RDEPENDS_${PN} = "tcl"
RRECOMMENDS_${PN} = "usb-modeswitch-data"
