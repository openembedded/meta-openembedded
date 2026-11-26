SUMMARY = "A library to access weather information from online services"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

GNOMEBN = "libgweather"
S = "${UNPACKDIR}/${GNOMEBN}-${PV}"

inherit gnomebase gsettings gobject-introspection gettext gi-docgen vala features_check

REQUIRED_DISTRO_FEATURES = "opengl"

SRC_URI += "file://0001-Allow-building-gir-in-cross-environments.patch"
SRC_URI[archive.sha256sum] = "7f5d0e8c9685ef2ff46c2f3a57cae48d7bf3540b2d83921f889ef28e6a876788"

# 40.0 is an older libgweather-3 release; odd minors are development releases
UPSTREAM_CHECK_REGEX = "libgweather-(?P<pver>4\.\d*[02468](\.\d+)+)\.tar"

GTKDOC_MESON_OPTION = "gtk_doc"
VALA_MESON_OPTION = "enable_vala"

DEPENDS = " \
    geocode-glib \
    gtk4 \
    gweather-locations \
    json-glib \
    libsoup-3.0 \
"

RDEPENDS:${PN} += "gweather-locations"

python __anonymous() {
    if d.getVar('GI_DATA_ENABLED') != 'True':
        raise bb.parse.SkipRecipe("depends on gweather-locations, which needs GI_DATA_ENABLED")
}

FILES:${PN} += " \
    ${datadir}/libgweather-4 \
    ${libdir}/libgweather-4 \
"
