SECTION = "libs"
LICENSE = "GD"
LIC_FILES_CHKSUM = "file://COPYING;md5=c97638cafd3581eb87abd37332137669"
DESCRIPTION = "gd is a library used to create PNG, JPEG, or WBMP images."
DEPENDS = "freetype libpng jpeg zlib"
PR = "r5"

SRC_URI = "http://www.libgd.org/releases/gd-2.0.36RC1.tar.gz"
SRC_URI[md5sum] = "39ac48e6d5e0012a3bd2248a0102f209"
SRC_URI[sha256sum] = "dd7c1795271221b9237769b96b8cec7fbdc5db7b8954d864ead51fc1296a6ac8"

S = "${WORKDIR}/gd-2.0.36RC1"

inherit autotools binconfig gettext

EXTRA_OECONF += " --with-zlib=${STAGING_LIBDIR}/.. \
                  --with-png=${STAGING_LIBDIR}/.. \
                  --with-jpeg=${STAGING_LIBDIR}/.. \
                  --with-freetype \
                  --without-fontconfig \
                  --without-xpm \
                  --without-x"

EXTRA_OEMAKE = 'LDFLAGS="${LDFLAGS}"'
