require lvm2.inc

SRC_URI[md5sum] = "c5a54ee0b86703daaad6e856439e115a"
SRC_URI[sha256sum] = "e120b066b85b224552efda40204488c5123de068725676fd6e5c8bc655051b94"

DEPENDS += "autoconf-archive-native"

TARGET_CC_ARCH += "${LDFLAGS}"

do_install() {
    oe_runmake 'DESTDIR=${D}' -C libdm install
}

RRECOMMENDS_${PN}_append_class-target = " lvm2-udevrules"

BBCLASSEXTEND = "native nativesdk"
