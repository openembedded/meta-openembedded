SUMMARY = "gd is a library used to create PNG, JPEG, or WBMP images"
DESCRIPTION = "The gd graphics library allows your code to quickly draw images \
complete with lines, arcs, text, multiple colors, cut and paste from other \
images, and flood fills, and to write out the result as a PNG or JPEG file. \
This is particularly useful in Web applications, where PNG and JPEG are two \
of the formats accepted for inline images by most browsers. Note that gd is not \
a paint program."
HOMEPAGE = "http://libgd.bitbucket.org/"

SECTION = "libs"
LICENSE = "GD"
LIC_FILES_CHKSUM = "file://COPYING;md5=c97638cafd3581eb87abd37332137669"
DEPENDS = "freetype libpng jpeg zlib tiff libvpx"

SRC_URI = "https://bitbucket.org/libgd/gd-libgd/downloads/libgd-${PV}.tar.bz2 \
           file://fix-the-subdir-objects-error.patch \
"
SRC_URI[md5sum] = "5a1d5bab3a4a41d9f111bcceee4ad25b"
SRC_URI[sha256sum] = "f3e1bc472bd81ee976a739436659fe752a14727a964c64530fde68531ddeee91"

S = "${WORKDIR}/libgd-${PV}"

inherit autotools binconfig gettext pkgconfig

EXTRA_OECONF += " --disable-rpath \
                  --with-jpeg=${STAGING_LIBDIR}/.. \
                  --with-freetype=yes \
                  --without-fontconfig \
                  --without-xpm \
                  --without-x"

EXTRA_OEMAKE = 'LDFLAGS="${LDFLAGS}"'

PACKAGES += "${PN}-tools"

FILES_${PN} = "${libdir}/lib*${SOLIBS}"
FILES_${PN}-tools = "${bindir}/*"

PROVIDES += "${PN}-tools"
RPROVIDES_${PN}-tools = "${PN}-tools"
RDEPENDS_${PN}-tools = "perl perl-module-strict"
