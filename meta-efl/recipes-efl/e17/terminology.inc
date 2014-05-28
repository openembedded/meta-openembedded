DESCRIPTION = "Enlightenment Terminal Emulator"
LICENSE = "MIT BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=9a8682b561bdfa219d59483a679ed80a"
DEPENDS = "evas ecore edje elementary eina eet emotion"

inherit e gettext

# Because latest release we have is 0.1.0 which sorts lower then 0.1.0+svnr${SRCPV} already in feeds
PE = "2"

EXTRA_OECONF = "\
    --with-edje-cc=${STAGING_BINDIR_NATIVE}/edje_cc \
"

FILES_${PN} += "\
    ${datadir}/icons/terminology.png \
"

# doesn't start without own theme
# ERR<770>:terminology utils.c:26 theme_apply() Could not load any theme for group=terminology/background: No Error
# CRI<770>:terminology main.c:448 elm_main() Couldn't find terminology theme! Forgot 'make install'?
RDEPENDS_${PN} += "${PN}-themes"
