DESCRIPTION = "libnl is a library for applications dealing with netlink sockets."
HOMEPAGE = "http://www.infradead.org/~tgr/libnl/"
SECTION = "libs/network"

LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=2b41e13261a330ee784153ecbb6a82bc"

DEPENDS = "flex-native bison-native"

SRC_URI = "http://www.infradead.org/~tgr/${BPN}/files/${BP}.tar.gz"

SRC_URI[md5sum] = "dbbb44801e833cf9ca4fdc943f2a58ee"
SRC_URI[sha256sum] = "69f4a360350ea61f2c988f947c251995c04093c74743eae9eeffba85846ef007"

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
                     ${libdir}/libnl-genl.so.*"

