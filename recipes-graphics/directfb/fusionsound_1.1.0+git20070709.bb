DESCRIPTION = "FusionSound for DirectFB"
DEPENDS = "directfb"
SECTION = "libs"
LICENSE = "LGPL"
PR = "r2"

SRC_URI = "git://git.directfb.org/git/directfb/core/FusionSound;protocol=git;tag=0b2162b570c8314fd1240b08d6ab4a8d074ccf37 \
	   file://fix-pkgconfig-cflags.patch \
	   file://use_limits_h.patch \
"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

CFLAGS_append  += " -I${STAGING_INCDIR}/directfb -I${STAGING_INCDIR}/directfb-internal"

#Below are some of the configure options
EXTRA_OECONF = " \
  		--disable-ieee-floats \
 	 	--disable-precision \
  		--enable-linear-filter \
  		--disable-dithering \
		--enable-debug \
  		--enable-examples \
  		--enable-module \
  		--disable-timidity \
  		--disable-wave \
		--enable-vorbis \
  		--enable-tremor \
  		--disable-mad \
  		--disable-cdda \
  		--disable-playlist \
		"

do_configure_append() {
    find ${S} -type f | xargs sed -i 's:I/usr/include:I${STAGING_INCDIR}:g'
}

do_install() {
        oe_runmake 'DESTDIR=${D}' install
}

FILES_fusionsound-dbg_append = " ${libdir}/directfb-1.1-0/*/*/.debug/*.so \
                              ${libdir}/directfb-1.1-0/*/.debug/*.so \
                          "

FILES_fusionsound-dev_append = " ${libdir}/directfb-1.1-0/interfaces/*/*.la \
			         ${libdir}/directfb-1.1-0/snddrivers/*.la \
			       "

FILES_fusionsound_append = " ${libdir}/directfb-1.1-0/interfaces/*/*.so \
                             ${libdir}/directfb-1.1-0/snddrivers/*.so \
			   "

