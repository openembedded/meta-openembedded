SUMMARY = "Enlightenment Web Browser"
LICENSE = "LGPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=6a6a8e020838b23406c81b19c1d46df6"
DEPENDS = "evas ecore edje elementary webkit-efl eldbus"

PE = "1"
PV = "0.3.0+gitr${SRCPV}"
SRCREV = "7bd6549982f4fc5e26e5d11e6c536601358892e3"

# webkit-efl isn't available for < armv7a
COMPATIBLE_MACHINE = "(-)"
COMPATIBLE_MACHINE_i586 = "(.*)"
COMPATIBLE_MACHINE_x86-64 = "(.*)"
COMPATIBLE_MACHINE_armv7a = "(.*)"

inherit e gettext
SRC_URI = " \
    git://git.enlightenment.org/apps/${BPN}.git \
    file://0001-eve-theme-updated-for-Openmoko-Freerunner-screen.patch \
    file://0002-adapt-to-webkit-efl-changes.patch \
"
S = "${WORKDIR}/git"

do_configure_prepend() {
    autopoint || touch config.rpath
}

EXTRA_OECONF = "\
    --with-edje-cc=${STAGING_BINDIR_NATIVE}/edje_cc \
"

FILES_${PN} += "\
    ${datadir}/icons/eve.png \
"
