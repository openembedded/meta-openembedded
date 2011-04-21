LICENSE = "GPL"
SECTION = "x11/gnome/libs"
DESCRIPTION = "A powerful object-oriented display"
PR = "r2"

inherit gnome

DEPENDS = "libglade libart-lgpl virtual/gail"

EXTRA_OECONF = "--disable-gtk-doc"

FILES_${PN} += "${libdir}/libglade/*/libcanvas.so"
FILES_${PN}-dbg += "${libdir}/libglade/*/.debug/"
FILES_${PN}-dev += "${libdir}/libglade/*/libcanvas.*a"
