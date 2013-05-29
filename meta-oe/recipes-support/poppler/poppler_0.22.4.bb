DESCRIPTION = "Poppler is a PDF rendering library based on the xpdf-3.0 code base."
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"

SRC_URI = " \
    http://poppler.freedesktop.org/${PN}-${PV}.tar.gz \
"
SRC_URI[md5sum] = "49d55921ce795778c7231fe9f2fe923b"
SRC_URI[sha256sum] = "4f438f34e63265e2da8427f3423f940ff41c26088922a9f5d976f795c1dce13b"

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
