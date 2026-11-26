SUMMARY = "Locations database for libgweather"
DESCRIPTION = "The world locations database used by libgweather, as XML \
source and as a precompiled GVariant database."
HOMEPAGE = "https://gitlab.gnome.org/GNOME/gweather-locations"
BUGTRACKER = "https://gitlab.gnome.org/GNOME/gweather-locations/-/issues"

LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=570a9b3749dd0463a1778803b12a6dce"

inherit gnomebase gettext python3native gobject-introspection-data

SRC_URI[archive.sha256sum] = "e7570a3661e0a752a06387b2032585cf588fc56770b1f9f39f1a55d17b2835fe"

# Releases are numbered <year>.<n>; there are no development series
UPSTREAM_CHECK_REGEX = "gweather-locations-(?P<pver>\d+\.\d+)\.tar"

# Locations.bin is generated with python3-gi, using the target GLib typelib
DEPENDS = "glib-2.0 python3-pygobject-native"
export GI_TYPELIB_PATH = "${STAGING_LIBDIR}/girepository-1.0"

python __anonymous() {
    if d.getVar('GI_DATA_ENABLED') != 'True':
        raise bb.parse.SkipRecipe("needs the GLib introspection typelib (GI_DATA_ENABLED)")
}

FILES:${PN} += " \
    ${datadir}/gweather-locations \
    ${libdir}/gweather-locations \
"
