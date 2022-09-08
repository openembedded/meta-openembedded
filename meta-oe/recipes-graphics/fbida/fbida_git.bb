SUMMARY = "Framebuffer image and doc viewer tools"
DESCRIPTION = "The fbida project contains a few applications for viewing and editing images, \
               with the main focus being photos."
HOMEPAGE = "https://www.kraxel.org/blog/linux/fbida/"
AUTHOR = "Gerd Hoffmann"
SECTION = "utils"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=e8feb78a32950a909621bbb51f634b39"

DEPENDS = "virtual/libiconv jpeg fontconfig freetype libexif libdrm pixman udev libinput poppler libepoxy cairo"

PV = "2.14+git${SRCPV}"
SRC_URI = "git://github.com/kraxel/fbida;protocol=https;branch=master \
           file://0001-Avoid-using-host-path.patch \
           file://fix-preprocessor.patch \
           file://support-jpeg-turbo.patch \
           file://fbida-gcc10.patch \
"
SRCREV = "ac9005bf0bbf50f14dc1b368be5084c8e0510a5d"
S = "${WORKDIR}/git"

inherit pkgconfig features_check

# Depends on libepoxy
REQUIRED_DISTRO_FEATURES = "opengl"

EXTRA_OEMAKE = "STRIP= 'srcdir=${S}' -f ${S}/GNUmakefile"

PACKAGECONFIG ??= "gif png"
PACKAGECONFIG[gif] = ",,giflib"
PACKAGECONFIG[png] = ",,libpng"
PACKAGECONFIG[tiff] = ",,tiff"
PACKAGECONFIG[motif] = ",,libx11 libxext libxpm libxt openmotif"
PACKAGECONFIG[webp] = ",,libwebp"

CFLAGS += "${@bb.utils.contains('DISTRO_FEATURES', 'x11', '', '-DEGL_NO_X11=1', d)}"

do_compile() {
    sed -i -e 's# fbgs# \$(srcdir)/fbgs#; s#-Ijpeg#-I\$(srcdir)/jpeg#; s# jpeg/# \$(srcdir)/jpeg/#' ${S}/GNUmakefile
    sed -i -e 's:/sbin/ldconfig:echo x:' ${S}/mk/Autoconf.mk
    sed -i -e 's: cpp: ${CPP}:' ${S}/GNUmakefile

    # Be sure to respect preferences (force to "no")
    # Also avoid issues when ${BUILD_ARCH} == ${HOST_ARCH}
    if [ -z "${@bb.utils.filter('PACKAGECONFIG', 'gif', d)}" ]; then
        sed -i -e '/^HAVE_LIBGIF/s/:=.*$/:= no/' ${S}/GNUmakefile
    fi
    if [ -z "${@bb.utils.filter('PACKAGECONFIG', 'png', d)}" ]; then
        sed -i -e '/^HAVE_LIBPNG/s/:=.*$/:= no/' ${S}/GNUmakefile
    fi
    if [ -z "${@bb.utils.filter('PACKAGECONFIG', 'tiff', d)}" ]; then
        sed -i -e '/^HAVE_LIBTIFF/s/:=.*$/:= no/' ${S}/GNUmakefile
    fi
    if [ -z "${@bb.utils.filter('PACKAGECONFIG', 'motif', d)}" ]; then
        sed -i -e '/^HAVE_MOTIF/s/:=.*$/:= no/' ${S}/GNUmakefile
    fi
    if [ -z "${@bb.utils.filter('PACKAGECONFIG', 'webp', d)}" ]; then
        sed -i -e '/^HAVE_LIBWEBP/s/:=.*$/:= no/' ${S}/GNUmakefile
    fi

    oe_runmake
}

do_install() {
    oe_runmake 'DESTDIR=${D}' install
}

RDEPENDS:${PN} = "ttf-dejavu-sans-mono"
