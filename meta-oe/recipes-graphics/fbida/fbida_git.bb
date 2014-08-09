SUMMARY = "Framebuffer image and doc viewer tools"
DESCRIPTION = "The fbida project contains a few applications for viewing and editing images, \
               with the main focus being photos."
HOMEPAGE = "http://linux.bytesex.org/fbida/"
AUTHOR = "Gerd Hoffmann"
SECTION = "utils"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=8ca43cbc842c2336e835926c2166c28b"

DEPENDS = "virtual/libiconv jpeg fontconfig freetype libexif curl"

SRC_URI = "git://git.kraxel.org/fbida"
SRCREV = "6aa5563cb3c8864ad15cf83eb6fca3b773da1099"
PV = "2.09+git${SRCPV}"
S = "${WORKDIR}/git"

EXTRA_OEMAKE = "STRIP="

PACKAGECONFIG ??= "gif png"
PACKAGECONFIG[gif] = ",,giflib"
PACKAGECONFIG[png] = ",,libpng"
PACKAGECONFIG[tiff] = ",,tiff"
PACKAGECONFIG[motif] = ",,libx11 libxext libxpm libxt openmotif"

do_compile() {
    sed -i -e 's:/sbin/ldconfig:echo x:' ${S}/mk/Autoconf.mk
    sed -i -e 's: cpp: ${TARGET_PREFIX}cpp -I${STAGING_INCDIR}:' ${S}/GNUmakefile

    # Be sure to respect preferences (force to "no")
    # Also avoid issues when ${BUILD_ARCH} == ${HOST_ARCH}
    if [ -z "${@base_contains('PACKAGECONFIG', 'gif', 'gif', '', d)}" ]; then
        sed -i -e '/^HAVE_LIBUNGIF/s/:=.*$/:= no/' ${S}/GNUmakefile
    fi
    if [ -z "${@base_contains('PACKAGECONFIG', 'png', 'png', '', d)}" ]; then
        sed -i -e '/^HAVE_LIBPNG/s/:=.*$/:= no/' ${S}/GNUmakefile
    fi
    if [ -z "${@base_contains('PACKAGECONFIG', 'tiff', 'tiff', '', d)}" ]; then
        sed -i -e '/^HAVE_LIBTIFF/s/:=.*$/:= no/' ${S}/GNUmakefile
    fi
    if [ -z "${@base_contains('PACKAGECONFIG', 'motif', 'motif', '', d)}" ]; then
        sed -i -e '/^HAVE_MOTIF/s/:=.*$/:= no/' ${S}/GNUmakefile
    fi

    oe_runmake
}

do_install() {
    oe_runmake 'DESTDIR=${D}' install
}

RDEPENDS_${PN} = "ttf-dejavu-sans-mono"
