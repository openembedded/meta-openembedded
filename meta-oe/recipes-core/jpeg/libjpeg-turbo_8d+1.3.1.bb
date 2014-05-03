DESCRIPTION = "libjpeg-turbo is a derivative of libjpeg that uses SIMD instructions (MMX, SSE2, NEON) to accelerate baseline JPEG compression and decompression"
HOMEPAGE = "http://libjpeg-turbo.org/"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://cdjpeg.h;endline=12;md5=78fa8dbac547bb5b2a0e6457a6cfe21d \
                    file://jpeglib.h;endline=14;md5=a08bb0a80f782a9f8da313cc4015ed6f \
                    file://djpeg.c;endline=9;md5=7629c51aed78174711c20a40194a8f1b \
"

BASEPV = "${@d.getVar('PV',True).split('+')[1]}"

SRC_URI = "${SOURCEFORGE_MIRROR}/${BPN}/${BPN}-${BASEPV}.tar.gz"
SRC_URI[md5sum] = "2c3a68129dac443a72815ff5bb374b05"
SRC_URI[sha256sum] = "c132907417ddc40ed552fe53d6b91d5fecbb14a356a60ddc7ea50d6be9666fb9"

S = "${WORKDIR}/${BPN}-${BASEPV}"

# Drop-in replacement for jpeg
PROVIDES = "jpeg"
RPROVIDES_${PN} += "jpeg"
RREPLACES_${PN} += "jpeg"
RCONFLICTS_${PN} += "jpeg"

inherit autotools pkgconfig

EXTRA_OECONF = "--with-jpeg8 "

PACKAGES =+ "jpeg-tools libturbojpeg"

DESCRIPTION_jpeg-tools = "The jpeg-tools package includes client programs to access libjpeg functionality.  These tools allow for the compression, decompression, transformation and display of JPEG files and benchmarking of the libjpeg library."
FILES_jpeg-tools = "${bindir}/*"

FILES_libturbojpeg = "${libdir}/libturbojpeg.so"
INSANE_SKIP_libturbojpeg = "dev-so"

BBCLASSEXTEND = "native"
DEPENDS = "nasm-native"

LEAD_SONAME = "libjpeg.so.8"
