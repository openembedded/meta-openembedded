DESCRIPTION = "Dates is a calendar application."
HOMEPAGE = "http://pimlico-project.org/dates.html"
BUGTRACKER = "https://bugzilla.gnome.org/"

LICENSE = "GPLv2 & GPLv2+ & LGPLv2+"

SECTION = "x11"
DEPENDS = "glib-2.0 gtk+ libglade evolution-data-server"
RDEPENDS_${PN} = "libedata-cal"

inherit autotools gettext pkgconfig gtk-icon-cache

# Poky/Sato specific enhancements
OWL = "--disable-owl"
OWL_poky = "--enable-owl --disable-dnd"

EXTRA_OECONF += "${OWL}"
DEPENDS_append_poky = " libowl"
SRC_URI_append_poky = " file://dates-owl-window-menu.patch;apply=yes "

