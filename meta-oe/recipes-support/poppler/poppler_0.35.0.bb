SUMMARY = "Poppler is a PDF rendering library based on the xpdf-3.0 code base"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"

SRC_URI = " \
    http://poppler.freedesktop.org/${BP}.tar.xz \
    file://0001-add-manadatory-options-to-find-qt4-qt5-moc.patch \
"
SRC_URI[md5sum] = "561ef4454bfabe59ef1b8b9cd4aa4c4b"
SRC_URI[sha256sum] = "e86755b1a4df6efe39d84f581c11bcb0e34976166a671a7b700c28ebaa3e2189"

DEPENDS = "fontconfig zlib cairo lcms"

inherit autotools pkgconfig gtk-doc

PACKAGECONFIG ??= "jpeg openjpeg png tiff ${@bb.utils.contains('BBFILE_COLLECTIONS', 'qt5-layer', 'qt5', '', d)}"
PACKAGECONFIG[jpeg] = "--enable-libjpeg,--disable-libjpeg,jpeg"
PACKAGECONFIG[png] = "--enable-libpng,--disable-libpng,libpng"
PACKAGECONFIG[tiff] = "--enable-libtiff,--disable-libtiff,tiff"
PACKAGECONFIG[curl] = "--enable-libcurl,--disable-libcurl,curl"
PACKAGECONFIG[openjpeg] = "--enable-libopenjpeg=openjpeg2,--disable-libopenjpeg,openjpeg"
PACKAGECONFIG[qt5] = "--enable-poppler-qt5 --with-moc-qt5=${STAGING_BINDIR_NATIVE}/qt5/moc,--disable-poppler-qt5,qtbase"

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
