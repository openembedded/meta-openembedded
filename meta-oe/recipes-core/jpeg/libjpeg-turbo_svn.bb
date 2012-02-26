DESCRIPTION = "libjpeg-turbo is a derivative of libjpeg that uses SIMD instructions (MMX, SSE2, NEON) to accelerate baseline JPEG compression and decompression"
HOMEPAGE = "http://libjpeg-turbo.org/"

LICENSE ="BSD-3-Clause"
LIC_FILES_CHKSUM = "file://cdjpeg.h;endline=12;md5=78fa8dbac547bb5b2a0e6457a6cfe21d \
                    file://jpeglib.h;endline=14;md5=7bb9a39828a1b1e84acd4e8aec1e5532 \
                    file://djpeg.c;endline=13;md5=e85613b52f2906c5dfc0e21ec902cb33 \
"
PV = "8c+1.2.0"
SRCREV = "733"
SRC_URI = "svn://libjpeg-turbo.svn.sourceforge.net/svnroot/libjpeg-turbo;proto=https;module=trunk"

S = "${WORKDIR}/trunk"

# Drop-in replacement for jpeg
PROVIDES = "jpeg"

inherit autotools pkgconfig

EXTRA_OECONF = "--with-jpeg8 "

PACKAGES =+         "jpeg-tools libturbojpeg"

DESCRIPTION_jpeg-tools = "The jpeg-tools package includes the client programs for access libjpeg functionality.  These tools allow for the compression, decompression, transformation and display of JPEG files."
FILES_jpeg-tools =  "${bindir}/*"

FILES_libturbojpeg = "${libdir}/libturbojpeg.so"
INSANE_SKIP_libturbojpeg = "dev-so"

BBCLASSEXTEND = "native"
DEPENDS_virtclass-native = "nasm-native"
