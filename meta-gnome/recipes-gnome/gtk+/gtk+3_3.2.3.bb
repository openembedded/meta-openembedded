SUMMARY = "Multi-platform toolkit for creating GUIs"
DESCRIPTION = "GTK+ is a multi-platform toolkit for creating graphical user interfaces. Offering a complete \
set of widgets, GTK+ is suitable for projects ranging from small one-off projects to complete application suites."
HOMEPAGE = "http://www.gtk.org"
BUGTRACKER = "https://bugzilla.gnome.org/"
SECTION = "libs"

DEPENDS = "glib-2.0 pango atk jpeg libpng libxext libxcursor \
           gtk-doc-native docbook-utils-native libxrandr libgcrypt \
           libxdamage libxrender libxcomposite cairo gdk-pixbuf gtk+-native"

LICENSE = "LGPLv2 & LGPLv2+ & LGPLv2.1+"

LIC_FILES_CHKSUM = "file://COPYING;md5=3bf50002aefd002f49e7bb854063f7e7 \
                    file://gtk/gtk.h;endline=27;md5=c59e0b4490dd135a5726ebf851f9b17f \
                    file://gdk/gdk.h;endline=27;md5=07db285ec208fb3e0bf7d861b0614202 \
                    file://tests/testgtk.c;endline=27;md5=ac85be7b810a1e9b00479af8e2018053"

SRC_URI = "http://download.gnome.org/sources/gtk+/3.2/gtk+-${PV}.tar.xz"
SRC_URI[md5sum] = "b4edcc69e39159dd7be17828249afb46"
SRC_URI[sha256sum] = "e2cf20f2510ebbc7be122a1a33dd1f472a7d06aaf16b4f2a63eb048cd9141d3d"

inherit autotools pkgconfig

S = "${WORKDIR}/gtk+-${PV}"

# Make it parallel installable with gtk+ 2.x
# The helper apps like gtk-update-iconcache won't get built here
EXTRA_OECONF += " \
                 --enable-gtk2-dependency \
                 --disable-glibtest \
                 --enable-modules \
                 --disable-cups \
"

# Make a symlink to our libtool
do_configure_prepend() {
	ln -s ${TARGET_PREFIX}libtool libtool || true
}

PACKAGES =+ "${PN}-demo"
LIBV = "3.0.0"

FILES_${PN}-demo = "${bindir}/gtk3-demo ${datadir}/gtk-3.0/demo"

FILES_${PN} = "${bindir}/gtk-update-icon-cache \
               ${bindir}/gtk-query-immodules-3.0 \
               ${libdir}/lib*${SOLIBS} \
               ${datadir}/themes ${sysconfdir} ${datadir}/glib-2.0/schemas/ \
               ${libdir}/gtk-3.0/${LIBV}/engines/libpixmap.so \
               ${libdir}/gtk-3.0/modules/*.so"

FILES_${PN}-dev += " \
                    ${datadir}/gtk-3.0/include \
                    ${libdir}/gtk-3.0/include \
                    ${libdir}/gtk-3.0/${LIBV}/loaders/*.la \
                    ${libdir}/gtk-3.0/${LIBV}/immodules/*.la \
                    ${libdir}/gtk-3.0/3.0.0/printbackends/*.la \
                    ${libdir}/gtk-3.0/${LIBV}/engines/*.la \
                    ${libdir}/gtk-3.0/modules/*.la \
                    ${bindir}/gtk-builder-convert"

FILES_${PN}-dbg += " \
                    ${libdir}/gtk-3.0/${LIBV}/loaders/.debug \
                    ${libdir}/gtk-3.0/${LIBV}/immodules/.debug \
                    ${libdir}/gtk-3.0/${LIBV}/engines/.debug \
                    ${libdir}/gtk-3.0/${LIBV}/printbackends/.debug \
                    ${libdir}/gtk-3.0/modules/.debug"


PACKAGES_DYNAMIC += "gtk3-immodule-* gtk3-printbackend-*"

python populate_packages_prepend () {
	import os.path

	prologue = bb.data.getVar("postinst_prologue", d, 1)

	gtk_libdir = bb.data.expand('${libdir}/gtk-3.0/${LIBV}', d)
	immodules_root = os.path.join(gtk_libdir, 'immodules')
	printmodules_root = os.path.join(gtk_libdir, 'printbackends');

	do_split_packages(d, immodules_root, '^im-(.*)\.so$', 'gtk3-immodule-%s', 'GTK input module for %s', prologue + 'gtk-query-immodules-3.0 > /etc/gtk-3.0/gtk.immodules')
	do_split_packages(d, printmodules_root, '^libprintbackend-(.*)\.so$', 'gtk3-printbackend-%s', 'GTK printbackend module for %s')

	if (bb.data.getVar('DEBIAN_NAMES', d, 1)):
		bb.data.setVar('PKG_${PN}', 'libgtk-3.0', d)
}

postinst_prologue() {
if [ "x$D" != "x" ]; then
  exit 1
fi

}

