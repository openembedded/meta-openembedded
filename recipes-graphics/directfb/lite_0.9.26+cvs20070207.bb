DESCRIPTION = "LiTE stands for Lightweight Toolkit Enabler. Its role is to \
		facilitate the functions of DirectFB so that a toolbox could \
                be written on top of DirectFB with less effort. As such LiTE \
                has abstractions for the underlying graphics and event systems."

DEPENDS = "directfb"
SECTION = "libs"
LICENSE = "LGPL"

# this is a CVS only release

SRC_URI = "cvs://anonymous@cvs.directfb.org/cvs/directfb;method=pserver;module=lite;date=${@bb.data.getVar('PV', d, 1)[10:]}"

S = "${WORKDIR}/lite"

inherit autotools pkgconfig

do_configure_append() {
    find ${S} -type f | xargs sed -i 's:I/usr/include:I${STAGING_INCDIR}:g'
}

do_install() {
        oe_runmake 'DESTDIR=${D}' install
}

FILES_lite-dbg_append = " ${libdir}/directfb-1.0-0/*/*/.debug/*.so \
                          ${libdir}/directfb-1.0-0/*/.debug/*.so \
                        "
FILES_lite-dev_append = " ${libdir}/directfb-1.0-0/interfaces/*/*.la \
			"

FILES_lite_append = " ${libdir}/directfb-1.0-0/interfaces/*/*.so \
                      ${datadir}/LiTE/* \
	              ${datadir}/fonts/truetype/* \
	            "

