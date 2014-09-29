SUMMARY = "Poppler is a PDF rendering library based on the xpdf-3.0 code base"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"

SRC_URI = " \
    http://poppler.freedesktop.org/${BP}.tar.xz \
"
SRC_URI[md5sum] = "786c943eee550e3a977c181e7778b1c8"
SRC_URI[sha256sum] = "de7de5fa337431e5d1f372e8577b3707322f1dbc1dc28a70f2927476f134d1ee"

DEPENDS = "fontconfig zlib cairo lcms"

inherit autotools pkgconfig gtk-doc

PACKAGECONFIG ??= "jpeg png tiff"
PACKAGECONFIG[jpeg] = "--enable-libjpeg,--disable-libjpeg,jpeg"
PACKAGECONFIG[png] = "--enable-libpng,--disable-libpng,libpng"
PACKAGECONFIG[tiff] = "--enable-libtiff,--disable-libtiff,tiff"

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
