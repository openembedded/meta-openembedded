DESCRIPTION = "Data files for usbmodeswitch"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

inherit allarch

SRC_URI = "http://www.draisberghof.de/usb_modeswitch/usb-modeswitch-data-${PV}.tar.bz2"
SRC_URI[md5sum] = "a7d23a03157871013a0d708ab2b1b6df"
SRC_URI[sha256sum] = "a74346a471d540ba9da7d7b332ad35ea05ff7375297c0da87da5be675293d5a5"

do_install() {
    oe_runmake install DESTDIR=${D}
}

RDEPENDS_${PN} = "usb-modeswitch"
FILES_${PN} += "${base_libdir}/udev/rules.d/ \
                ${datadir}/usb_modeswitch"
