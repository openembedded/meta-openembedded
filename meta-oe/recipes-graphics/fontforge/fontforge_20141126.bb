SUMMARY = "A font editor"
HOMEPAGE = "http://fontforge.github.io/en-US/"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = " \
    file://COPYING.gplv3;md5=d32239bcb673463ab874e80d47fae504 \
    file://LICENSE;md5=71d636ba7678baa1573e1b531041386e \
"

inherit autotools pkgconfig pythonnative

DEPENDS = "glib-2.0 pango giflib tiff libxml2 jpeg python libtool"
DEPENDS_append_class-target = " libxi"

SRC_URI = "https://github.com/${BPN}/${BPN}/archive/${PV}.tar.gz"
SRC_URI[md5sum] = "0d6f5112c4e5689087339a94aceadf77"
SRC_URI[sha256sum] = "9c90c640023463c0b2b97b87575a014fcf44f688c3763f4cd136d3c267092106"

EXTRA_OECONF_append_class-native = " with_x=no --disable-python-extension --disable-python-scripting"

do_configure_prepend() {
    currdir=`pwd`
    cd ${S}
    ./bootstrap --force
    cd $currdir
}

EXTRA_OEMAKE = "CFLAGS='${CFLAGS} -I${B}/uthash/src'"

PACKAGES =+ "${PN}-python-dbg ${PN}-python"

FILES_${PN} += " \
    ${datadir}/mime \
    ${datadir}/icons \
"

FILES_${PN}-python = "${PYTHON_SITEPACKAGES_DIR} ${datadir}/${PN}/python"
FILES_${PN}-python-dbg = "${PYTHON_SITEPACKAGES_DIR}/.debug"
RDEPENDS_${PN}-python = "python"

# for e.g kde's oxygen-fonts
BBCLASSEXTEND = "native"
