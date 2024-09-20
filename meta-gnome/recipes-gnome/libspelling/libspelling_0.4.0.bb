SUMMARY = "A spellcheck library for GTK 4"
HOMEPAGE = "https://gitlab.gnome.org/GNOME/libspelling"
LICENSE = "LGPL-2.1-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

DEPENDS = "enchant2 gtk4 gtksourceview5 icu"

inherit gnomebase pkgconfig gettext gi-docgen vala gobject-introspection

GIR_MESON_OPTION = ''
GIDOCGEN_MESON_OPTION = 'docs'

PACKAGECONFIG ?= ""
PACKAGECONFIG[sysprof] = "-Dsysprof=true,-Dsysprof=false,sysprof"

SRC_URI[archive.sha256sum] = "00c63970d708a0ef3bcba40e708a06d7030114cb9f210c74583ffad56d36e3dd"
