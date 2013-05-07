DESCRIPTION = "Poppler is a PDF rendering library based on the xpdf-3.0 code base."
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"

SRC_URI = "http://poppler.freedesktop.org/${PN}-${PV}.tar.gz"
SRC_URI[md5sum] = "12658f3308597e57f3faff538cc73baf"
SRC_URI[sha256sum] = "33421148cdc8e043da291dece2cce1cea6220d49a50c00c56d56d6435501d42e"

DEPENDS = "fontconfig jpeg zlib gtk+ cairo tiff lcms"

PR = "r1"

inherit autotools pkgconfig

EXTRA_OECONF = "\
    --enable-xpdf-headers \
    --disable-gtk-test \
    --disable-poppler-qt \
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
