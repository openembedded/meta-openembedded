DESCRIPTION = "Task list application"
HOMEPAGE = "http://pimlico-project.org/tasks.html"
BUGTRACKER = "https://bugzilla.gnome.org/"

LICENSE = "GPLv2 & GPLv2+"

SECTION = "x11"
DEPENDS = "glib-2.0 gtk+ evolution-data-server"
DEPENDS_append_poky = " libowl"

inherit autotools pkgconfig gtk-icon-cache

OWL ?= "--disable-owl"
OWL_poky ?= "--enable-owl"

EXTRA_OECONF += "${OWL}"
