SUMMARY = "Poppler is a PDF rendering library based on the xpdf-3.0 code base"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"

SRC_URI = " \
    http://poppler.freedesktop.org/${BP}.tar.xz \
    file://0001-add-manadatory-options-to-find-qt4-qt5-moc.patch \
    file://0002-fix-gcc-6-math-ambiguous-errors.patch \
"
SRC_URI[md5sum] = "1a4c0cd873bddd8f266b85ab8d799962"
SRC_URI[sha256sum] = "c9c93318b789d3933f6e0bad3bc65110280c28eac3f0666284bb9c9a0ab4bc36"

DEPENDS = "fontconfig zlib cairo lcms"

inherit autotools pkgconfig gtk-doc gobject-introspection

PACKAGECONFIG ??= "jpeg openjpeg png tiff nss ${@bb.utils.contains('BBFILE_COLLECTIONS', 'qt5-layer', 'qt5', '', d)}"
PACKAGECONFIG[jpeg] = "--enable-libjpeg,--disable-libjpeg,jpeg"
PACKAGECONFIG[png] = "--enable-libpng,--disable-libpng,libpng"
PACKAGECONFIG[tiff] = "--enable-libtiff,--disable-libtiff,tiff"
PACKAGECONFIG[curl] = "--enable-libcurl,--disable-libcurl,curl"
PACKAGECONFIG[openjpeg] = "--enable-libopenjpeg=openjpeg2,--disable-libopenjpeg,openjpeg"
PACKAGECONFIG[qt5] = "--enable-poppler-qt5 --with-moc-qt5=${STAGING_BINDIR_NATIVE}/qt5/moc,--disable-poppler-qt5,qtbase"
PACKAGECONFIG[qt4e] = "--enable-poppler-qt4 --with-moc-qt4=${STAGING_BINDIR_NATIVE}/moc4,--disable-poppler-qt4,qt4-embedded"
PACKAGECONFIG[nss] = "--enable-libnss,--disable-libnss,nss"

# Needed for qt5
CXXFLAGS += "--std=c++11"

#| /usr/src/debug/glibc/2.25-r0/git/csu/elf-init.c:87: undefined reference to `__init_array_end'
#| /usr/src/debug/glibc/2.25-r0/git/csu/elf-init.c:87: undefined reference to `__init_array_start'
#| /mnt/a/build/tmp-glibc/sysroots/x86_64-linux/usr/libexec/arm-oe-linux-gnueabi/gcc/arm-oe-linux-gnueabi/6.2.0/ld: .libs/libpoppler.so.65.0.0: hidden symbol `__init_array_end' isn't defined 

SECURITY_CFLAGS = "${SECURITY_NO_PIE_CFLAGS}"

EXTRA_OECONF = "\
    --enable-xpdf-headers \
    --disable-gtk-test \
    --enable-zlib \
"

do_compile_prepend() {
    export GIR_EXTRA_LIBS_PATH="${B}/poppler/.libs"
}

# Adjust library names when building for QT4e
QT4E_PATCHES = "${@bb.utils.contains('PACKAGECONFIG', 'qt4e', 'file://fix-qt4e-library-dependencies.patch', '', d)}"
SRC_URI_append = "${QT4E_PATCHES}"

# check for TARGET_FPU=soft and inform configure of the result so it can disable some floating points
def get_poppler_fpu_setting(bb, d):
    if d.getVar('TARGET_FPU') in [ 'soft' ]:
        return "--enable-fixedpoint"
    return ""

EXTRA_OECONF += "${@get_poppler_fpu_setting(bb, d)}"

PACKAGES =+ "libpoppler libpoppler-glib"
FILES_libpoppler = "${libdir}/libpoppler.so.*"
FILES_libpoppler-glib = "${libdir}/libpoppler-glib.so.*"

RDEPENDS_libpoppler = "poppler-data"
