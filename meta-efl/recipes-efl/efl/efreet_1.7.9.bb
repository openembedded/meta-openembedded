DESCRIPTION = "The Enlightenment freedesktop.org library"
DEPENDS = "ecore"
LICENSE = "MIT BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=9594ec75c5a57e71fccedcbe10dd3ef4"

inherit efl gettext

PACKAGES =+ "${PN}-mime ${PN}-trash"
FILES_${PN}-mime = "${libdir}/libefreet_mime.so.*"
FILES_${PN}-trash = "${libdir}/libefreet_trash.so.*"

# efreet_desktop_cache_create is needed for e-wm start, don't include it in -tests
FILES_${PN} += "${libdir}/efreet/efreet_desktop_cache_create \
                ${libdir}/efreet/efreet_icon_cache_create \
"

SRC_URI = "\
    ${E_MIRROR}/${SRCNAME}-${SRCVER}.tar.gz \
"

SRC_URI[md5sum] = "5972833cf78ca93440403b94ed4ed74d"
SRC_URI[sha256sum] = "d5037c04ac8130879bf87d924e6b83cf479e7dee8235321ed1a49f97083b611b"
