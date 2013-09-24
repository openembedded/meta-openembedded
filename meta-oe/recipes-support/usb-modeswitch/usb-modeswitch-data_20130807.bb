DESCRIPTION = "Data files for usbmodeswitch"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

inherit allarch

SRC_URI = "http://www.draisberghof.de/usb_modeswitch/usb-modeswitch-data-${PV}.tar.bz2"
SRC_URI[md5sum] = "91feff51deba6e48e78506b8f4db2274"
SRC_URI[sha256sum] = "a3114e2c1f38eed3ad0067df30e53a96313908a9539a8ea5d94a4d35651699eb"

do_install() {
    oe_runmake install DESTDIR=${D}
}

RDEPENDS_${PN} = "usb-modeswitch"
FILES_${PN} += "${base_libdir}/udev/rules.d/ \
                ${datadir}/usb_modeswitch"
