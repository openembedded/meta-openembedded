SUMMARY = "Data files for usbmodeswitch"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

inherit allarch

DEPENDS += "tcl-native"

SRC_URI = "http://www.draisberghof.de/usb_modeswitch/${BP}.tar.bz2"
SRC_URI[sha256sum] = "221507574da7d11debdba94e40cf164b59fb8b19be1a3ecbec76e348b7a4fa73"

do_install() {
    oe_runmake install DESTDIR=${D} RULESDIR=${D}/${nonarch_base_libdir}/udev/rules.d
}

RDEPENDS:${PN} = "usb-modeswitch (>= 2.4.0)"
FILES:${PN} += "${nonarch_base_libdir}/udev/rules.d/ \
                ${datadir}/usb_modeswitch"
