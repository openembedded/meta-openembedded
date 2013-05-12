DESCRIPTION = "Poppler is a PDF rendering library based on the xpdf-3.0 code base."
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"

PR = "r2"

SRC_URI = " \
    http://poppler.freedesktop.org/${PN}-${PV}.tar.gz \
    file://0001-splash-Splash.cc-add-cast-to-fix-build-for-enable-fi.patch \
"
SRC_URI[md5sum] = "be915388eaad6865cee5a156c8119ec1"
SRC_URI[sha256sum] = "916a931cbef951366014f13d5f4a574657ccc064ac41051df96dd58e22934431"

DEPENDS = "fontconfig jpeg zlib gtk+ cairo tiff lcms"

inherit autotools pkgconfig

EXTRA_OECONF = "\
    --enable-xpdf-headers \
    --disable-gtk-test \
    --disable-poppler-qt4 \
    --enable-zlib \
"

# check for TARGET_FPU=soft and inform configure of the result so it can disable some floating points
def get_poppler_fpu_setting(bb, d):
    if d.getVar('TARGET_FPU', 1) in [ 'soft' ]:
        return "--enable-fixedpoint"
    return ""

EXTRA_OECONF += "${@get_poppler_fpu_setting(bb, d)}"

PACKAGES =+ "libpoppler libpoppler-glib"
FILES_libpoppler = "${libdir}/libpoppler.so.*"
FILES_libpoppler-glib = "${libdir}/libpoppler-glib.so.*"

RDEPENDS_${PN} = "poppler-data"
