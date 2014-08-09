SUMMARY = "WebP is an image format designed for the Web"
DESCRIPTION = "WebP is a method of lossy and lossless compression that can be \
               used on a large variety of photographic, translucent and \
               graphical images found on the web. The degree of lossy \
               compression is adjustable so a user can choose the trade-off \
               between file size and image quality. WebP typically achieves \
               an average of 30% more compression than JPEG and JPEG 2000, \
               without loss of image quality."
HOMEPAGE = "https://developers.google.com/speed/webp/"
SECTION = "libs"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://PATENTS;md5=7ec8a34de2073ea13ea2991006246d20"

DEPENDS = "giflib"

SRC_URI = "https://webp.googlecode.com/files/${BP}.tar.gz"
SRC_URI[md5sum] = "c8dd1d26eb9566833aba269b86d97e68"
SRC_URI[sha256sum] = "31913577e96386556855b41d210736449445fe96cfbe9289014e9b8afa944d69"

EXTRA_OECONF = " \
    --disable-experimental \
    --disable-wic \
    --enable-libwebpmux \
    --enable-libwebpdemux \
    --enable-threading \
"

inherit autotools lib_package

PACKAGECONFIG ??= ""

# libwebpdecoder is a subset of libwebp, don't build it unless requested
PACKAGECONFIG[decoder] = "--enable-libwebpdecoder,--disable-libwebpdecoder"

# Apply for examples programs: cwebp and dwebp
PACKAGECONFIG[jpeg] = ",ac_cv_header_jpeglib_h=no,jpeg"
PACKAGECONFIG[png] = ",ac_cv_header_png_h=no,libpng"
PACKAGECONFIG[tiff] = ",ac_cv_header_tiffio_h=no,tiff"

# Apply only for example program vwebp
PACKAGECONFIG[gl] = ",,mesa-glut"

PACKAGES =+ "${PN}-gif2webp"

DESCRIPTION_${PN}-gif2webp = "Simple tool to convert animated GIFs to WebP"
FILES_${PN}-gif2webp = "${bindir}/gif2webp"
