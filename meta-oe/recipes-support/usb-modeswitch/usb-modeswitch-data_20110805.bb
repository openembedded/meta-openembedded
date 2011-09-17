DESCRIPTION = "Data files for usbmodeswitch"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

inherit allarch

SRC_URI = "http://www.draisberghof.de/usb_modeswitch/usb-modeswitch-data-${PV}.tar.bz2 \
          "
SRC_URI[md5sum] = "0ed8a28f8efd3177a128ecd46fc8bf9f"
SRC_URI[sha256sum] = "9453fae3af30974297946de0442f539a0bae46f06c1e97921d63d6f91a19139b"

do_install() {
	oe_runmake install DESTDIR=${D}
}

DEPENDS_${PN} = "\
    usb-modeswitch \
    tcl \ 
" 
FILES_${PN} += "${base_libdir}/udev/rules.d/ \
                ${datadir}/usb_modeswitch"
