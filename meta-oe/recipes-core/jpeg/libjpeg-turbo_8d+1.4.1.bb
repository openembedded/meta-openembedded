DESCRIPTION = "libjpeg-turbo is a derivative of libjpeg that uses SIMD instructions (MMX, SSE2, NEON) to accelerate baseline JPEG compression and decompression"
HOMEPAGE = "http://libjpeg-turbo.org/"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://cdjpeg.h;endline=12;md5=cad955d15145c3fdceec6855e078e953 \
                    file://jpeglib.h;endline=14;md5=dfc803dc51ae21178d1376ec73c4454d \
                    file://djpeg.c;endline=9;md5=e93a8f2061e8a0ac71c7a485c10489e2 \
"

DEPENDS = "nasm-native"

BASEPV = "${@d.getVar('PV',True).split('+')[1]}"

SRC_URI = "${SOURCEFORGE_MIRROR}/${BPN}/${BPN}-${BASEPV}.tar.gz"
SRC_URI[md5sum] = "b1f6b84859a16b8ebdcda951fa07c3f2"
SRC_URI[sha256sum] = "4bf5bad4ce85625bffbbd9912211e06790e00fb982b77724af7211034efafb08"

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

LEAD_SONAME = "libjpeg.so.8"
