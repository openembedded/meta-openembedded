DESCRIPTION = "The Openobex project is an open source implementation of the \
Object Exchange (OBEX) protocol."
HOMEPAGE = "http://openobex.triq.net"
SECTION = "libs"
LICENSE = "GPL-2.0-only & LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=eb723b61539feef013de476e68b5c50a \
                    file://COPYING.LIB;md5=a6f89e2100d9b6cdffcea4f398e37343 \
"

DEPENDS = "virtual/libusb0"
DEPENDS:append:class-target = " bluez5"

SRC_URI = "${SOURCEFORGE_MIRROR}/${BPN}/${BPN}/${PV}/${BP}-Source.tar.gz \
"
SRC_URI[sha256sum] = "158860aaea52f0fce0c8e4b64550daaae06df2689e05834697b7e8c7d73dd4fc"

UPSTREAM_CHECK_URI = "https://sourceforge.net/projects/openobex/files/openobex/"
UPSTREAM_CHECK_REGEX = "${BPN}/(?P<pver>\d+(\.\d+)+)"

S = "${WORKDIR}/${BP}-Source"

inherit cmake pkgconfig

EXTRA_OECONF = " -DCMAKE_SKIP_RPATH=ON "
EXTRA_OECMAKE += "-DBUILD_DOCUMENTATION=OFF"

ASNEEDED = ""

do_install:append () {
    rmdir ${D}${bindir}
}

PACKAGES =+ "openobex-apps"
FILES:${PN}-apps = "${bindir}/*"
FILES:${PN} += "${libdir}/lib*.so.*"
FILES:${PN}-dev += "${bindir}/openobex-config"
DEBIAN_NOAUTONAME:${PN}-apps = "1"

BBCLASSEXTEND = "native"
