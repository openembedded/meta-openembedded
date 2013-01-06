SUMMARY = "Multi-platform toolkit for creating GUIs"
DESCRIPTION = "GTK+ is a multi-platform toolkit for creating graphical user interfaces. Offering a complete \
set of widgets, GTK+ is suitable for projects ranging from small one-off projects to complete application suites."
HOMEPAGE = "http://www.gtk.org"
BUGTRACKER = "https://bugzilla.gnome.org/"
SECTION = "libs"

DEPENDS = "glib-2.0 pango atk jpeg libpng libxext libxcursor \
           gtk-doc-native docbook-utils-native libxrandr libgcrypt \
           libxdamage libxrender libxcomposite libxi cairo gdk-pixbuf gtk+-native"

LICENSE = "LGPLv2 & LGPLv2+ & LGPLv2.1+"

LIC_FILES_CHKSUM = "file://COPYING;md5=5f30f0716dfdd0d91eb439ebec522ec2 \
                    file://gtk/gtk.h;endline=25;md5=1d8dc0fccdbfa26287a271dce88af737 \
                    file://gdk/gdk.h;endline=25;md5=c920ce39dc88c6f06d3e7c50e08086f2 \
                    file://tests/testgtk.c;endline=25;md5=cb732daee1d82af7a2bf953cf3cf26f1"

SRC_URI = "http://download.gnome.org/sources/gtk+/3.4/gtk+-${PV}.tar.xz \
"

SRC_URI[md5sum] = "1b2cf29502a6394e8d4b30f7f5bb9131"
SRC_URI[sha256sum] = "f154e460075034da4c0ce89c320025dcd459da2a1fdf32d92a09522eaca242c7"

PR = "r0"

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

FILES_${PN}-demo = "${bindir}/gtk3-demo \
                    ${datadir}/gtk-3.0/demo \
                    ${bindir}/gtk3-demo-application \
                    ${bindir}/gtk3-widget-factory \
"

FILES_${PN} = "${bindir}/gtk-update-icon-cache \
               ${bindir}/gtk-query-immodules-3.0 \
               ${libdir}/lib*${SOLIBS} \
               ${datadir}/themes ${sysconfdir} ${datadir}/glib-2.0/schemas/ \
               ${datadir}/gtk-3.0/gtkbuilder.rng \
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


PACKAGES_DYNAMIC += "^gtk3-immodule-.* ^gtk3-printbackend-.*"

python populate_packages_prepend () {
    import os.path

    prologue = d.getVar("postinst_prologue", 1)

    gtk_libdir = d.expand('${libdir}/gtk-3.0/${LIBV}')
    immodules_root = os.path.join(gtk_libdir, 'immodules')
    printmodules_root = os.path.join(gtk_libdir, 'printbackends');

    do_split_packages(d, immodules_root, '^im-(.*)\.so$', 'gtk3-immodule-%s', 'GTK input module for %s', prologue + 'gtk-query-immodules-3.0 > /etc/gtk-3.0/gtk.immodules')
    do_split_packages(d, printmodules_root, '^libprintbackend-(.*)\.so$', 'gtk3-printbackend-%s', 'GTK printbackend module for %s')

    if (d.getVar('DEBIAN_NAMES', 1)):
        d.setVar('PKG_${PN}', 'libgtk-3.0')
}

postinst_prologue() {
if [ "x$D" != "x" ]; then
  exit 1
fi

}

