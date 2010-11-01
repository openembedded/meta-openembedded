DESCRIPTION = "An Internet printing system for Unix."
SECTION = "console/utils"
LICENSE = "GPL LGPL"
DEPENDS = "gnutls libpng jpeg dbus dbus-glib zlib fakeroot-native"
PROVIDES = "cups14"

SRC_URI = "ftp://ftp.easysw.com/pub/cups/${PV}/cups-${PV}-source.tar.bz2"

LEAD_SONAME = "libcupsdriver.so"

inherit autotools binconfig

EXTRA_OECONF = " \
                --enable-gnutls \
		--enable-dbus \
		--enable-browsing \
                --disable-openssl \
		--disable-tiff \
		--without-php \
		--without-perl \
		--without-python \
		--without-java \
               "


do_configure() {
	gnu-configize
	libtoolize --force
	DSOFLAGS="${LDFLAGS}" oe_runconf
}

do_compile () {
	sed -i s:STRIP:NOSTRIP: Makedefs
	sed -i s:serial:: backend/Makefile

	echo "all:"    >  man/Makefile
	echo "libs:" >> man/Makefile
	echo "install:" >> man/Makefile
	echo "install-data:" >> man/Makefile
	echo "install-exec:" >> man/Makefile
	echo "install-headers:" >> man/Makefile
	echo "install-libs:" >> man/Makefile

	oe_runmake "SSLLIBS=-lgnutls -L${STAGING_LIBDIR}" \
		   "LIBPNG=-lpng -lm -L${STAGING_LIBDIR}" \
		   "LIBJPEG=-ljpeg -L${STAGING_LIBDIR}" \
		   "LIBZ=-lz -L${STAGING_LIBDIR}" \
		   "-I."
}

fakeroot do_install () {
	oe_runmake "DSTROOT=${D}" install

   # This directory gets installed with perms 511, which makes packaging fail
   chmod 0711 "${D}/${localstatedir}/run/cups/certs"
}

python do_package_append() {
	# Change permissions back the way they were, they probably had a reason...
	workdir = bb.data.getVar('WORKDIR', d, 1)
	os.system('chmod 0511 %s/install/cups/var/run/cups/certs' % workdir)
}

PACKAGES =+ "${PN}-lib ${PN}-libimage"

FILES_${PN}-lib = "${libdir}/libcups.so.*"

FILES_${PN}-libimage = "${libdir}/libcupsimage.so.*"

FILES_${PN}-dbg += "${libdir}/cups/backend/.debug \
                    ${libdir}/cups/cgi-bin/.debug \
		    ${libdir}/cups/filter/.debug \
		    ${libdir}/cups/monitor/.debug \
		    ${libdir}/cups/notifier/.debug \
		    ${libdir}/cups/daemon/.debug \
		    "
#package the html for the webgui inside the main packages (~1MB uncompressed)

FILES_${PN} += "${datadir}/doc/cups/images \
		${datadir}/doc/cups/*html \
		${datadir}/doc/cups/*.css \
                ${datadir}/icons/ \
	       "
