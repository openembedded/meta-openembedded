DESCRIPTION = "The Enlightenment Window Manager Version 17"
DEPENDS = "eet evas eina ecore edje efreet edbus eeze"
LICENSE = "MIT BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=2667a0f4120372ea91f7467cdff4095f"
SRCNAME = "e"
PV = "0.16.999.060+svnr${SRCPV}"
SRCREV = "${EFL_SRCREV}"

inherit e update-alternatives gettext
S = "${WORKDIR}/${SRCNAME}"

SRC_URI = "\
  ${E_SVN}/trunk;module=${SRCNAME};proto=http \
  file://enlightenment_start.oe \
  file://applications.menu \
"

EXTRA_OECONF = "\
  --with-edje-cc=${STAGING_BINDIR_NATIVE}/edje_cc \
  --with-eet-eet=${STAGING_BINDIR_NATIVE}/eet \
  --x-includes=${STAGING_INCDIR}/X11 \
  --x-libraries=${STAGING_LIBDIR} \
  --enable-simple-x11 \
"

do_configure_prepend() {
	autopoint || true
}

do_install_append() {
    # customising - should rather make this simple upstream
    install -m 755 ${WORKDIR}/enlightenment_start.oe ${D}/${bindir}

    install -d ${D}/${datadir}/applications/
    install -m 644 ${S}/src/modules/fileman/module.desktop ${D}/${datadir}/applications/efm.desktop
    sed "s#Type=Link#Type=Application#g" -i ${D}/${datadir}/applications/efm.desktop
    echo "Exec=enlightenment_remote -efm-open-dir" >> ${D}/${datadir}/applications/efm.desktop
    echo "Terminal=false" >> ${D}/${datadir}/applications/efm.desktop
    echo "Categories=Application;" >> ${D}/${datadir}/applications/efm.desktop
    echo "StartupNotify=true" >> ${D}/${datadir}/applications/efm.desktop
    install -d ${D}/${datadir}/icons/
    install -m 644 ${S}/data/themes/images/icon_icon_theme.png ${D}/${datadir}/icons/e-module-fileman.png

    install -d ${D}/${sysconfdir}/xdg/menus
    install -m 644 ${WORKDIR}/applications.menu ${D}/${sysconfdir}/xdg/menus/
    for I in `find ${D}/${libdir}/enlightenment -name "*.a" -print`; do rm -f $I; done
    for I in `find ${D}/${libdir}/enlightenment -name "*.la" -print`; do rm -f $I; done
}

RDEPENDS_${PN} += "\
  shared-mime-info \
  mime-support \
  edje-utils \
  ${PN}-utils \
  dbus-x11 \
"

# Uclibc build don't have 'glibc-utils'
RDEPENDS_${PN}_append_libc-glibc = " glibc-utils "

# The systray module used to be external, but is part of e-wm now
RREPLACES_${PN} = "systray"

PACKAGES =+ "\
  ${PN}-config-default \
  ${PN}-config-illume2 \
  ${PN}-config-minimalist \
  ${PN}-config-netbook \
  ${PN}-config-scaleable \
  ${PN}-config-standard \
  ${PN}-theme-default \
  ${PN}-background-dark-gradient \
  ${PN}-background-light-gradient \
  ${PN}-images \
  ${PN}-icons \
  ${PN}-other \
  ${PN}-input-methods \
  ${PN}-sysactions \
  ${PN}-utils \
  ${PN}-menu \
  efm-desktop-icon \
  illume-keyboard-default-alpha \
  illume-keyboard-default-numeric \
  illume-keyboard-default-terminal \
"

ESYSACTIONS ?= "${PN}-sysactions"

RRECOMMENDS_${PN} = "\
  ${PN}-config-default \
  ${PN}-images \
  ${PN}-icons \
  ${PN}-other \
  ${PN}-input-methods \
  ${ESYSACTIONS} \
"

FILES_${PN} = "\
  ${bindir}/* \
  ${libdir}/enlightenment/utils/* \
  ${libdir}/enlightenment/modules/*/*.* \
  ${libdir}/enlightenment/modules/*/*/* \
  ${libdir}/enlightenment/modules/*/*/.order \
  ${libdir}/enlightenment/*plugins/*/*/* \
  ${libdir}/enlightenment/preload/e_precache.so \
  ${datadir}/enlightenment/data/icons \
  ${datadir}/enlightenment/data/input_methods \
  ${datadir}/enlightenment/data/config/profile.cfg \
  ${datadir}/enlightenment/AUTHORS \
  ${datadir}/enlightenment/COPYING \
  ${datadir}/xsessions/enlightenment.desktop \
  ${sysconfdir}/xdg \
"

FILES_${PN}-config-default = "${datadir}/enlightenment/data/config/default"
FILES_${PN}-config-illume2 = "${datadir}/enlightenment/data/config/illume"
FILES_${PN}-config-minimalist = "${datadir}/enlightenment/data/config/minimalist"
FILES_${PN}-config-netbook = "${datadir}/enlightenment/data/config/netbook"
FILES_${PN}-config-scaleable = "${datadir}/enlightenment/data/config/scaleable"
FILES_${PN}-config-standard = "${datadir}/enlightenment/data/config/standard"
FILES_${PN}-theme-default = "${datadir}/enlightenment/data/themes/default.edj"
FILES_${PN}-theme-default = "${datadir}/enlightenment/data/themes/default.edj"
FILES_${PN}-background-dark-gradient = "${datadir}/enlightenment/data/backgrounds/Dark_Gradient.edj"
FILES_${PN}-background-light-gradient = "${datadir}/enlightenment/data/backgrounds/Light_Gradient.edj"
FILES_${PN}-images = "${datadir}/enlightenment/data/images"
FILES_${PN}-icons = "${datadir}/enlightenment/data/icons"
FILES_${PN}-other = "${datadir}/enlightenment/data/other"
FILES_${PN}-input-methods = "${datadir}/enlightenment/data/input_methods"
FILES_${PN}-sysactions = "${sysconfdir}/enlightenment/sysactions.conf"
FILES_${PN}-utils = "${libdir}/enlightenment/utils/*"
FILES_${PN}-menu = "${sysconfdir}/xdg/menus/applications.menu"

FILES_efm-desktop-icon = "\
  ${datadir}/applications/efm.desktop \
  ${datadir}/icons/e-module-fileman.png \
"

KEYBOARDS_DIR="${libdir}/enlightenment/modules/illume-keyboard/keyboards"
FILES_illume-keyboard-default-alpha = "\
  ${KEYBOARDS_DIR}/Default.kbd \
  ${KEYBOARDS_DIR}/alpha.png \
"
FILES_illume-keyboard-default-numeric = "\
  ${KEYBOARDS_DIR}/Numbers.kbd \
  ${KEYBOARDS_DIR}/numeric.png \
"
FILES_illume-keyboard-default-terminal = "\
  ${KEYBOARDS_DIR}/Terminal.kbd \
  ${KEYBOARDS_DIR}/qwerty.png \
"

RRECOMMENDS_${PN}-config-default = "${PN}-theme-default"
RRECOMMENDS_${PN}-config-illume2 = "\
  illume-keyboard-default-alpha \
  illume-keyboard-default-numeric \
  illume-keyboard-default-terminal \
"

RRECOMMENDS_${PN}-config-minimalist = "\
  ${PN}-background-light-gradient \
  ${PN}-theme-default \
"
RRECOMMENDS_${PN}-config-netbook = "\
  ${PN}-background-dark-gradient \
  ${PN}-theme-default \
"
RRECOMMENDS_${PN}-config-scaleable = "${PN}-theme-default"
RRECOMMENDS_${PN}-config-standard = "${PN}-theme-default"

FILES_${PN}-dbg += "\
  ${libdir}/enlightenment/modules/*/*/.debug/ \
  ${libdir}/enlightenment/preload/.debug/ \
  ${libdir}/enlightenment/utils/.debug/ \
  ${libdir}/enlightenment/*plugins/*/*/.debug \
"

FILES_${PN}-doc += "\
  ${datadir}/enlightenment/doc \
"

CONFFILES_${PN}-menu = "${sysconfdir}/xdg/menus/applications.menu"
CONFFILES_${PN}-sysactions = "/etc/enlightenment/sysactions.conf"

ALTERNATIVE_PATH = "${bindir}/enlightenment_start.oe"
ALTERNATIVE_NAME = "x-window-manager"
ALTERNATIVE_LINK = "${bindir}/x-window-manager"
ALTERNATIVE_PRIORITY = "16"
