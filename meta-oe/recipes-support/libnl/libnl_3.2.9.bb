DESCRIPTION = "libnl is a library for applications dealing with netlink sockets."
HOMEPAGE = "http://www.infradead.org/~tgr/libnl/"
SECTION = "libs/network"

PE = "1"

LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

DEPENDS = "flex-native bison-native"

SRC_URI = "http://www.infradead.org/~tgr/${BPN}/files/${BP}.tar.gz"

SRC_URI[md5sum] = "c13adec0239b266207fff07d79e5ce9e"
SRC_URI[sha256sum] = "9f23e9460bd9bb7fbe09af5eb281e4a43a26fa245ea864ed5e28fe4e8118af63"

inherit autotools pkgconfig

do_install_append() {
    # make wpa-supplicant happy
    # TODO: when moving to oe-core fix at wpa-supplicant
    # (e.g src/drivers/drivers.mak)
    cd ${D}${includedir}
    ln -sf libnl3/netlink netlink
    cd ${D}${libdir}
    ln -sf libnl-3.so.200 libnl.so
    ln -sf libnl-3.so.200 libnl.so.3
    ln -sf libnl-genl-3.so.200 libnl-genl.so
    ln -sf libnl-genl-3.so.200 libnl-genl.so.3
}

FILES_${PN} = "${libdir}/libnl-3.so.* \
               ${libdir}/libnl.so.* \
               ${sysconfdir}"
RREPLACES_${PN} = "libnl2"
RCONFLICTS_${PN} = "libnl2"
FILES_${PN}-dbg += "${libdir}/libnl/cli/*/.debug"
FILES_${PN}-dev += "${libdir}/libnl/cli/*/*.so \
                    ${libdir}/libnl/cli/*/*.la"
FILES_${PN}-staticdev += "${libdir}/libnl/cli/*/*.a"

PACKAGES += "${PN}-cli ${PN}-route ${PN}-nf ${PN}-genl"
FILES_${PN}-cli   = "${libdir}/libnl-cli-3.so.* \
                     ${libdir}/libnl/cli/*/*.so.* \
                     ${sbindir}/nl-*"
FILES_${PN}-route = "${libdir}/libnl-route-3.so.*"
FILES_${PN}-nf    = "${libdir}/libnl-nf-3.so.*"
FILES_${PN}-genl  = "${libdir}/libnl-genl-3.so.* \
                     ${libdir}/libnl-genl.so.* \
                     ${sbindir}/genl-ctrl-list"
RREPLACES_${PN}-genl = "libnl-genl2 libnl-genl-3-200"
RCONFLICTS_${PN}-genl = "libnl-genl2 libnl-genl-3-200"
