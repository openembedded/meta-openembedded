DESCRIPTION = "The Enlightenment freedesktop.org library"
DEPENDS = "ecore"
LICENSE = "MIT BSD"

inherit efl

INC_PR = "r0"

PACKAGES =+ "${PN}-mime ${PN}-trash"
FILES_${PN}-mime = "${libdir}/libefreet_mime.so.*"
FILES_${PN}-trash = "${libdir}/libefreet_trash.so.*"

# efreet_desktop_cache_create is needed for e-wm start, don't include it in -tests
FILES_${PN} += "${libdir}/efreet/efreet_desktop_cache_create \
                ${libdir}/efreet/efreet_icon_cache_create \
"
