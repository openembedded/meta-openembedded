require gtk+.inc

SRC_URI_append_virtclass-native = " file://no-demos.patch \
"
SRC_URI_append = "file://gtk-dnd-grab-deadlock-fix.patch \
		  file://cross-nm.patch \
		  file://xkb-ifdef.patch \
		 "

SRC_URI[gtk.md5sum] = "53e6f3a93bd22934878fc4a4a34c68aa"
SRC_URI[gtk.sha256sum] = "0e081731d21e34ff45c82199490c2889504fa8b3c7e117c043e82ababaec0f65"

PR = "${INC_PR}.4"

BBCLASSEXTEND = "native"

ARM_INSTRUCTION_SET = "arm"

DEPENDS_virtclass-native = "libpng-native atk-native pango-native cairo-native libxrender-native libxext-native libgcrypt-native"
PROVIDES_virtclass-native = "gdk-pixbuf-csource-native"

# Enable xkb selectively
XKBTOGGLE = " --disable-xkb"
XKBTOGGLE_angstrom = ""

EXTRA_OECONF = " --enable-introspection=no --with-libtiff ${XKBTOGGLE} --disable-glibtest gio_can_sniff=yes"
EXTRA_OECONF_append_virtclass-native = " --without-libtiff --without-libjpeg --disable-cups"

PACKAGES_DYNAMIC = "gtk-module-* gdk-pixbuf-loader-* gtk-immodule-* gtk-printbackend-*"

python populate_packages_prepend () {
	import os.path

	prologue = bb.data.getVar("postinst_prologue", d, 1)

	gtk_libdir = bb.data.expand('${libdir}/gtk-2.0/${LIBV}', d)
	loaders_root = os.path.join(gtk_libdir, 'loaders')
	immodules_root = os.path.join(gtk_libdir, 'immodules')
	printmodules_root = os.path.join(gtk_libdir, 'printbackends');
	modules_root = bb.data.expand('${libdir}/gtk-2.0/modules/',d)

	do_split_packages(d, loaders_root, '^libpixbufloader-(.*)\.so$', 'gdk-pixbuf-loader-%s', 'GDK pixbuf loader for %s', prologue + 'gdk-pixbuf-query-loaders > /etc/gtk-2.0/gdk-pixbuf.loaders', extra_depends='')
	do_split_packages(d, immodules_root, '^im-(.*)\.so$', 'gtk-immodule-%s', 'GTK input module for %s', prologue + 'gtk-query-immodules-2.0 > /etc/gtk-2.0/gtk.immodules', extra_depends='')
	do_split_packages(d, printmodules_root, '^libprintbackend-(.*)\.so$', 'gtk-printbackend-%s', 'GTK printbackend module for %s', extra_depends='')
	do_split_packages(d, modules_root, '^lib(.*)\.so$', 'gtk-module-%s', 'GTK module for %s', extra_depends='')

        if (bb.data.getVar('DEBIAN_NAMES', d, 1)):
                bb.data.setVar('PKG_${PN}', 'libgtk-2.0', d)
}

# gail is part of gtk+ since gtk+-2.13.0 (targetting >=GNOME 2.23):
PROVIDES = "virtual/gail"
RPROVIDES_${PN} = "libgailutil18"
RCONFLICTS_${PN} = "libgailutil18"
RREPLACES_${PN} = "libgailutil18"
RPROVIDES_${PN}-dev = "libgailutil-dev"
RCONFLICTS_${PN}-dev = "libgailutil-dev"
RREPLACES_${PN}-dev = "libgailutil-dev"
RPROVIDES_${PN}-doc = "libgailutil-doc"
RCONFLICTS_${PN}-doc = "libgailutil-doc"
RREPLACES_${PN}-doc = "libgailutil-doc"
RPROVIDES_${PN}-dbg = "libgailutil-dbg"
RCONFLICTS_${PN}-dbg = "libgailutil-dbg"
RREPLACES_${PN}-dbg = "libgailutil-dbg"
# FIXME: replace locales as well

