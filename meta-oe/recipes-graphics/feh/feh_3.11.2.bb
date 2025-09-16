SUMMARY = "X11 image viewer aimed mostly at console users"
HOMEPAGE = "https://feh.finalrewind.org/"
SECTION = "x11/utils"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=275a549d1abc07e3bae1d7137f53f602"
DEPENDS = "\
    imlib2 \
    virtual/libx11 libxt\
"

SRC_URI = "https://feh.finalrewind.org/feh-${PV}.tar.bz2"
SRC_URI[sha256sum] = "020f8bce84c709333dcc6ec5fff36313782e0b50662754947c6585d922a7a7b2"

inherit mime-xdg features_check
# depends on virtual/libx11
REQUIRED_DISTRO_FEATURES = "x11"

EXTRA_OEMAKE = "curl=0 xinerama=0 PREFIX=/usr"

do_compile () {
     oe_runmake
}

do_install () {
     oe_runmake install app=1 'DESTDIR=${D}' 'ICON_PREFIX=${D}${datadir}/icons'
}

RDEPENDS:${PN} += "imlib2-loaders"

FILES:${PN} += "${datadir}/icons"
