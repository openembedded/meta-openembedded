DESCRIPTION = "ImageMagick is an image convertion tools"
SECTION = "console/utils"
LICENSE = "ImageMagick"
LIC_FILES_CHKSUM = "file://LICENSE;md5=944f66dcedc98d5a4e5d964bd3b32e7b"
# FIXME: There is much more checked libraries. All should be added or explicitly disabled to get consistent results.
DEPENDS = "lcms bzip2 jpeg libpng librsvg tiff zlib"

PR = "r2"

PATCHSET = "6"
SRC_URI = "ftp://ftp.nluug.nl/pub/ImageMagick/ImageMagick-${PV}-${PATCHSET}.tar.bz2 \
           file://PerlMagic_MakePatch.patch \
	   file://remove.dist-lzip.patch \
          "
SRC_URI[md5sum] = "bcf07cf0822572fa2b47fa6d506f699a"
SRC_URI[sha256sum] = "d9347f31d3d35b40009e1bbcf713c5ad252411e05ee621d5a7dae01e47bdbd80"

S = "${WORKDIR}/ImageMagick-${PV}-${PATCHSET}"

inherit autotools binconfig pkgconfig

# xml disabled because it's using xml2-config --prefix to determine prefix which returns just /usr with our libxml2
# if someone needs xml support then fix it first
EXTRA_OECONF = "--program-prefix= --without-x --without-freetype --without-perl --disable-openmp --without-xml"

FILES_${PN} += "${libdir}/ImageMagick-${PV}/modules-Q16/*/*.so \
                ${libdir}/ImageMagick-${PV}/modules-Q16/*/*.la \
                ${libdir}/ImageMagick-${PV}/modules-Q16/filters \
                ${libdir}/ImageMagick-${PV}/modules-Q16/coders \
                ${libdir}/ImageMagick-${PV}/config/ \
                ${datadir}/ImageMagick-${PV}"

FILES_${PN}-dev += "${libdir}/ImageMagick-${PV}/modules-Q16/*/*.a"

FILES_${PN}-dbg += "${libdir}/ImageMagick-${PV}/modules-Q16/*/.debug/*"

BBCLASSEXTEND = "native"

LEAD_SONAME = "libMagickCore.so.*"

do_configure_prepend() {
    export ac_cv_sys_file_offset_bits=yes
}
