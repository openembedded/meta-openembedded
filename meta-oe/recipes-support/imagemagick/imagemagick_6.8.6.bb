DESCRIPTION = "ImageMagick is an image convertion tools"
SECTION = "console/utils"
LICENSE = "ImageMagick"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b1bf06a8d02cb00b02dea7d03ef9525f"
# FIXME: There is much more checked libraries. All should be added or explicitly disabled to get consistent results.
DEPENDS = "lcms bzip2 jpeg libpng librsvg tiff zlib fftw freetype"

PATCHSET = "10"
SRC_URI = "http://www.imagemagick.org/download/legacy/ImageMagick-${PV}-${PATCHSET}.tar.bz2 \
           file://remove.dist-lzip.patch \
"
SRC_URI[md5sum] = "9034ab5bf6d63fe72230779f1f590691"
SRC_URI[sha256sum] = "95b487bb0e4ebf97dd06b6d61f421a05e2666f253ae06352cb3262b34603b2a3"

S = "${WORKDIR}/ImageMagick-${PV}-${PATCHSET}"

inherit autotools binconfig pkgconfig

# xml disabled because it's using xml2-config --prefix to determine prefix which returns just /usr with our libxml2
# if someone needs xml support then fix it first
EXTRA_OECONF = "--program-prefix= --without-x --without-perl --disable-openmp --without-xml --disable-opencl"

PACKAGECONFIG ??= ""
PACKAGECONFIG[jp2] = "--with-jp2,--without-jp2,jasper"

FILES_${PN} += "${libdir}/ImageMagick-${PV}/modules-Q16/*/*.so \
                ${libdir}/ImageMagick-${PV}/modules-Q16/*/*.la \
                ${libdir}/ImageMagick-${PV}/modules-Q16/filters \
                ${libdir}/ImageMagick-${PV}/modules-Q16/coders \
                ${libdir}/ImageMagick-${PV}/config-Q16 \
                ${datadir}/ImageMagick-6 "

FILES_${PN}-dev += "${libdir}/ImageMagick-${PV}/modules-Q16/*/*.a"

FILES_${PN}-dbg += "${libdir}/ImageMagick-${PV}/modules-Q16/*/.debug/*"

BBCLASSEXTEND = "native"

LEAD_SONAME = "libMagickCore.so.*"

do_configure_prepend() {
    export ac_cv_sys_file_offset_bits=yes
}
