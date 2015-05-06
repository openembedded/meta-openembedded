SUMMARY = "display dialog boxes from shell scripts"
DESCRIPTION = "Dialog lets you to present a variety of questions \
or display messages using dialog boxes from a shell \
script (or any scripting language)."
HOMEPAGE = "http://invisible-island.net/dialog/"
SECTION = "console/utils"
DEPENDS = "ncurses"
LICENSE = "LGPL-2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=a6f89e2100d9b6cdffcea4f398e37343"

SRC_URI[md5sum] = "38ef59a7daa5459119cb06777c735e4b"
SRC_URI[sha256sum] = "6844b13a7a1fea568a8d5bb3004e1af90888cd4a5e8c2ded2c38f34fcc7397ff"

SRC_URI = "ftp://invisible-island.net/dialog/dialog-${PV}.tgz \
           file://use-pkg-config-for-ncurses-detection.patch \
          "

# hardcoded here for use in dialog-static recipe
S = "${WORKDIR}/dialog-${PV}"

inherit autotools-brokensep pkgconfig

EXTRA_OECONF = "--with-ncurses \
                --disable-rpath-hack"

do_configure() {
    gnu-configize --force
    sed -i 's,${cf_ncuconfig_root}6-config,${cf_ncuconfig_root}-config,g' -i configure
    oe_runconf
}
