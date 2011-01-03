DESCRIPTION = "A C++ Wrapper for the directfb framebuffer library."
HOMEPAGE = "http://directfb.org"
SECTION = "libs"
DEPENDS = "directfb"
LICENSE = "LGPL"

PR = "r1"
SRC_URI = "http://www.directfb.org/downloads/Extras/++DFB-${PV}.tar.gz \
	    file://dfb-api-change.patch"
S = "${WORKDIR}/++DFB-${PV}"
RV = "0.9-25"

inherit autotools pkgconfig

do_install() {
        oe_runmake 'DESTDIR=${D}' install
}
FILES_++dfb_append = " ${libdir}/*.so*"

SRC_URI[md5sum] = "bfc2c45be253a5f25576587f61faff1b"
SRC_URI[sha256sum] = "bc5b7766318ef6676defdf0103d1b53b48a3fee6b819fbe2076461caa9f94ec9"
