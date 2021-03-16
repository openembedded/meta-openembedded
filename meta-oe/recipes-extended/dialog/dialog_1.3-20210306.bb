SUMMARY = "display dialog boxes from shell scripts"
DESCRIPTION = "Dialog lets you to present a variety of questions \
or display messages using dialog boxes from a shell \
script (or any scripting language)."
HOMEPAGE = "http://invisible-island.net/dialog/"
SECTION = "console/utils"
DEPENDS = "ncurses"
LICENSE = "LGPL-2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=a6f89e2100d9b6cdffcea4f398e37343"

SRC_URI = "ftp://ftp.invisible-island.net/${BPN}/${BP}.tgz \
          "
SRC_URI[md5sum] = "87793125f9bd3b8e84165f99da2a2134"
SRC_URI[sha256sum] = "a73e7b607b635f63c0202bb3313106e700f91fe4a9f4d26ca7003f6eb2b9cb0f"

# hardcoded here for use in dialog-static recipe
S = "${WORKDIR}/dialog-${PV}"

inherit autotools-brokensep pkgconfig

PACKAGECONFIG ??= "${@bb.utils.filter('DISTRO_FEATURES', 'x11', d)}"

PACKAGECONFIG[x11] = "--with-x --x-includes=${STAGING_INCDIR} --x-libraries=${STAGING_LIBDIR},--without-x,virtual/libx11"

EXTRA_OECONF = "--with-ncurses \
                --disable-rpath-hack"

do_configure() {
    gnu-configize --force
    sed -i 's,${cf_ncuconfig_root}6-config,${cf_ncuconfig_root}-config,g' -i configure
    sed -i 's,cf_have_ncuconfig=unknown,cf_have_ncuconfig=yes,g' -i configure
    oe_runconf
}
