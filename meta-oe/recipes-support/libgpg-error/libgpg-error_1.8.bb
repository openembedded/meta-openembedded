DESCRIPTION = "a small library that defines common error values for all GnuPG components"
HOMEPAGE = "http://www.gnupg.org/related_software/libgpg-error/"
BUGTRACKER = "https://bugs.g10code.com/gnupg/index"

BBCLASSEXTEND = "native"

LICENSE = "GPLv2+ & LGPLv2.1+"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552 \
                    file://COPYING.LIB;md5=2d5025d4aa3495befef8f17206a5b0a1 \
                    file://src/gpg-error.h;endline=23;md5=83c16c8f5cea85affa1ff270a6f4fcff \
                    file://src/init.c;endline=20;md5=b69742f2a8827d494c6f6a4b1768416c"
SRC_URI[md5sum] = "1d2005268a2f096db28cf9cf77b3229a"
SRC_URI[sha256sum] = "bdfbf2677147239d2d26d4e37f44635d9fd97c133c9ef9fd9793a12a059508c5"

SECTION = "libs"
LICENSE = "GPLv2+ & LGPLv2.1+"
PR = "r0"

SRC_URI = "ftp://ftp.gnupg.org/gcrypt/libgpg-error/libgpg-error-${PV}.tar.bz2 \
           file://pkgconfig.patch;"

# move libgpg-error-config into -dev package
FILES_${PN} = "${libdir}/lib*.so.*"
FILES_${PN}-dev += "${bindir}/*"

inherit autotools binconfig pkgconfig gettext
