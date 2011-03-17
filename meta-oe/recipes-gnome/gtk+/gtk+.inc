DESCRIPTION = "GTK+ is a multi-platform toolkit for creating graphical user interfaces. Offering a complete \
set of widgets, GTK+ is suitable for projects ranging from small one-off projects to complete application suites."
HOMEPAGE = "http://www.gtk.org"
SECTION = "libs"
LICENSE = "LGPL"
PRIORITY = "optional"
DEPENDS = "glib-2.0 tiff pango atk jpeg libpng libxext libxcursor gtk-doc libgcrypt cups \
	   libxrandr libxdamage libxrender libxcomposite cairo jasper gtk+-native"
PROVIDES = "gdk-pixbuf"

MAJ_VER = "${@bb.data.getVar('PV',d,1).split('.')[0]}.${@bb.data.getVar('PV',d,1).split('.')[1]}"
SRC_URI = "http://download.gnome.org/sources/gtk+/${MAJ_VER}/gtk+-${PV}.tar.bz2;name=gtk \
	file://hardcoded_libtool.patch \
	file://run-iconcache.patch \
	file://toggle-font.diff;striplevel=0 \
	file://xsettings.patch \
	"

INC_PR = "r10"

inherit autotools pkgconfig

LEAD_SONAME = "libgtk-x11-2.0.so"
LIBV = "2.10.0"

PACKAGES =+ "${PN}-demo ${PN}-demo-source"
FILES_${PN}-demo = "${bindir}/gtk-demo"
FILES_${PN}-demo-source = "${datadir}/gtk-2.0/demo"

FILES_${PN} = "${bindir}/gdk-pixbuf-query-loaders \
	${bindir}/gtk-update-icon-cache \
	${bindir}/gtk-query-immodules-2.0 \
	${libdir}/lib*.so.* \
	${datadir}/themes ${sysconfdir} \
	${libdir}/gtk-2.0/${LIBV}/engines/libpixmap.so"

FILES_${PN}-dev += " \
	${datadir}/gtk-2.0/include \
	${libdir}/gtk-2.0/include \
	${bindir}/gdk-pixbuf-csource \
	${bindir}/gtk-builder-convert"

FILES_${PN}-dbg += " \
	${libdir}/gtk-2.0/modules/.debug/* \
	${libdir}/gtk-2.0/${LIBV}/loaders/.debug/* \
	${libdir}/gtk-2.0/${LIBV}/immodules/.debug/* \
	${libdir}/gtk-2.0/${LIBV}/engines/.debug/* \
	${libdir}/gtk-2.0/${LIBV}/printbackends/.debug/*"

NEATSTUFF = "ttf-dejavu-sans gdk-pixbuf-loader-png gdk-pixbuf-loader-jpeg gdk-pixbuf-loader-gif gdk-pixbuf-loader-xpm"
NEATSTUFF_append_linux = " glibc-gconv-iso8859-1"
NEATSTUFF_append_linux-gnueabi = " glibc-gconv-iso8859-1"

# Only add runtime dependencies for target recipe
RDEPENDS_${PN} += "${@['', 'hicolor-icon-theme']['${PN}' == '${BPN}']}"
RRECOMMENDS_${PN} += "${@['', '${NEATSTUFF}']['${PN}' == '${BPN}']}"

do_install_append () {
	mkdir -p ${D}${libdir}/gtk-2.0/include
	install -m 0644 gdk/gdkconfig.h ${D}${libdir}/gtk-2.0/include/gdkconfig.h
	# Copy over all headers, since the maemo stuff needs access to the private api. *sigh*
	cp gtk/*.h ${D}${includedir}/gtk-2.0/gtk/

	install -m 0644 m4macros/gtk-2.0.m4 ${STAGING_DATADIR}/aclocal/

	install -d ${D}${sysconfdir}/gtk-2.0
	rm -f ${D}${libdir}/gtk-2.0/*/*.la ${D}${libdir}/gtk-2.0/${LIBV}/*/*.la
}

do_install_append_virtclass-native () {
	${D}${bindir}/gdk-pixbuf-query-loaders \
		${D}${libdir}/gtk-2.0/${LIBV}/loaders/*.so | \
		sed -e's,${D},,' > \
			${D}${sysconfdir}/gtk-2.0/gdk-pixbuf.loaders
}

SYSROOT_PREPROCESS_FUNCS_append_virtclass-native = " gdk_pixbuf_fixup"

_SYSROOT_DEST = "${SYSROOT_DESTDIR}${STAGING_DIR_HOST}"
gdk_pixbuf_fixup () {
	create_wrapper ${_SYSROOT_DEST}${bindir}/gdk-pixbuf-query-loaders \
		LD_LIBRARY_PATH=${STAGING_LIBDIR} \
		GDK_PIXBUF_MODULEDIR=${libdir}/gtk-2.0/${LIBV}/loaders
	${_SYSROOT_DEST}${bindir}/gdk-pixbuf-query-loaders \
		${_SYSROOT_DEST}${libdir}/gtk-2.0/${LIBV}/loaders/*.so | \
		sed -e's,${_SYSROOT_DEST},,' > \
			${_SYSROOT_DEST}${sysconfdir}/gtk-2.0/gdk-pixbuf.loaders
	create_wrapper ${_SYSROOT_DEST}${bindir}/gdk-pixbuf-csource \
		GDK_PIXBUF_MODULE_FILE=${sysconfdir}/gtk-2.0/gdk-pixbuf.loaders
}

postinst_prologue() {
if [ "x$D" != "x" ]; then
  exit 1
fi

}
