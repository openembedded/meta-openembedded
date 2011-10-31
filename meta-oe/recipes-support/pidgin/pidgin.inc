DESCRIPTION = "multi-protocol instant messaging client"
SECTION = "x11/network"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"
DEPENDS = "python startup-notification avahi gtk+ ncurses gnutls virtual/libintl gstreamer dbus"
INC_PR = "r2"

inherit autotools gettext pkgconfig gconf perlnative

EXTRA_OECONF = " \
		--enable-vv \
		--disable-perl \
		--disable-tcl \
		--disable-gevolution \
		--disable-schemas-install \
		--x-includes=${STAGING_INCDIR} \
		--x-libraries=${STAGING_LIBDIR} \
		--enable-gnutls=yes \
		--with-ncurses-headers=${STAGING_INCDIR} \
		--with-gnutls-includes=${STAGING_INCDIR} \
		--with-gnutls-libs=${STAGING_LIBDIR} \
		"

do_configure_prepend() {
	touch ${S}/po/Makefile
}

OE_LT_RPATH_ALLOW=":${libdir}/purple-2:"
OE_LT_RPATH_ALLOW[export]="1"

PACKAGES =+ "libpurple-dbg libpurple-dev libpurple libgnt-dbg libgnt libgnt-dev finch-dbg finch finch-dev ${PN}-data"

LEAD_SONAME = "libpurple.so.0"
FILES_libpurple     = "${libdir}/libpurple*.so.* ${libdir}/purple-2 ${bindir}/purple-* ${sysconfdir}/gconf/schemas/purple* ${datadir}/purple/ca-certs"
FILES_libpurple-dev = "${libdir}/libpurple*.la \
                       ${libdir}/libpurple*.so \
                       ${libdir}/purple-2/*.la \
                       ${libdir}/purple-2/libjabber.so \
                       ${libdir}/purple-2/liboscar.so \
                       ${libdir}/purple-2/libymsg.so \
                       ${datadir}/aclocal"
FILES_libpurple-dbg += "${libdir}/.debug/libpurple* \
                        ${libdir}/purple-2/.debug"
FILES_libgnt         = "${libdir}/libgnt.so.* ${libdir}/gnt/*.so" 
FILES_libgnt-dev     = "${libdir}/gnt/*.la" 
FILES_libgnt-dbg     = "${libdir}/gnt/.debug \
FILES_finch          = "${bindir}/finch"
FILES_finch-dev      = "${libdir}/finch/*.la"
FILES_finch-dbg      = "${bindir}/.debug/finch \
                        ${libdir}/finch/.debug"

FILES_${PN} = "${bindir} ${datadir}/${PN} ${libdir}/${PN}/*.so \
	       ${datadir}/applications"
RRECOMMENDS_${PN} = "${PN}-data libpurple-plugin-ssl-gnutls libpurple-protocol-irc libpurple-protocol-xmpp"

FILES_${PN}-data = "${datadir}/pixmaps ${datadir}/sounds ${datadir}/icons"
FILES_${PN}-dev += "${libdir}/${PN}/*.la"

PACKAGES_DYNAMIC = "libpurple-protocol-* libpurple-plugin-* pidgin-plugin-* finch-plugin-*"

python populate_packages_prepend () {
	pidgroot = bb.data.expand('${libdir}/pidgin', d)
	purple   = bb.data.expand('${libdir}/purple-2', d)
	finch    = bb.data.expand('${libdir}/finch', d)

	do_split_packages(d, pidgroot, '^([^l][^i][^b].*)\.so$',
		output_pattern='pidgin-plugin-%s',
		description='Pidgin plugin %s',
		prepend=True, extra_depends='')

	do_split_packages(d, purple, '^lib(.*)\.so$',
		output_pattern='libpurple-protocol-%s',
		description='Libpurple protocol plugin for %s',
		prepend=True, extra_depends='')

	do_split_packages(d, purple, '^(ssl-.*)\.so$',
		output_pattern='libpurple-plugin-%s',
		description='libpurple plugin %s',
		prepend=True, extra_depends='libpurple-plugin-ssl')

	do_split_packages(d, purple, '^([^l][^i][^b].*)\.so$',
		output_pattern='libpurple-plugin-%s',
		description='libpurple plugin %s',
		prepend=True, extra_depends='')

	do_split_packages(d, finch, '^([^l][^i][^b].*)\.so$',
		output_pattern='finch-plugin-%s',
		description='Finch plugin %s',
		prepend=True, extra_depends='')
}
