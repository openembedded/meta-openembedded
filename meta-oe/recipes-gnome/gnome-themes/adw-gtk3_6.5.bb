SUMMARY = "An unofficial GTK3 port of libadwaita."
HOMEPAGE = "https://github.com/lassekongo83/adw-gtk3"
SECTION = "graphics"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/LGPL-2.1-only;md5=1a6d268fd218675ffea8be556788b780"

# Building needs Dart Sass, which OE lacks, so install upstream's prebuilt CSS
SRC_URI = "https://github.com/lassekongo83/adw-gtk3/releases/download/v${PV}/adw-gtk3v${PV}.tar.xz;subdir=${BP}"
SRC_URI[sha256sum] = "a81780fadfc432be0fc3d89c4ebb41aa28e4f032d42c36f9789c57dd10cfa41c"

UPSTREAM_CHECK_URI = "https://github.com/lassekongo83/adw-gtk3/releases"
UPSTREAM_CHECK_REGEX = "releases/tag/v(?P<pver>\d+(\.\d+)+)"

inherit allarch

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    install -d ${D}${datadir}/themes
    cp -R --no-preserve=ownership ${S}/adw-gtk3 ${S}/adw-gtk3-dark ${D}${datadir}/themes/
}

FILES:${PN} = "${datadir}/themes"
