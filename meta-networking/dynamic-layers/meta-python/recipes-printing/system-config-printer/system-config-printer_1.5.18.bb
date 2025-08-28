DESCRIPTION = "a graphical user interface that allows the user to change print settings"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI = "\
    git://github.com/OpenPrinting/system-config-printer.git;protocol=https;branch=master \
    file://0001-setup-prefix-and-root.patch \
"

SRCREV = "895d3dec50c93bfd4f142bac9bfcc13051bf84cb"
S = "${WORKDIR}/git"

inherit autotools gettext pkgconfig python3native features_check

REQUIRED_DISTRO_FEATURES = "gobject-introspection-data"

DEPENDS = "cups glib-2.0 libusb xmlto-native desktop-file-utils-native autoconf-archive-native python3-setuptools-native"

PACKAGECONFIG ?= "${@bb.utils.filter('DISTRO_FEATURES', 'systemd polkit', d)}"
PACKAGECONFIG[systemd] = ",--without-systemdsystemunitdir,systemd"
PACKAGECONFIG[polkit] = ",,,cups-pk-helper"


# Makefile.am will honor multilib
EXTRA_OEMAKE += " \
    DESTDIR=${D} \
    prefix=${prefix} \
    PYTHON=${PYTHON} \
    pythonsitedir=${PYTHON_SITEPACKAGES_DIR} \
"

do_configure:prepend() {
    # This file is not provided if fetching from git but required for configure
    touch ${S}/ChangeLog
}

do_install:prepend(){
    install -d ${D}${PYTHON_SITEPACKAGES_DIR}
}

do_install:append() {
    cp -rf ${B}/cupshelpers.egg-info ${D}${PYTHON_SITEPACKAGES_DIR}
    cp -rf ${B}/cupshelpers ${D}${PYTHON_SITEPACKAGES_DIR}
    rm -rf ${D}${PYTHON_SITEPACKAGES_DIR}/*.egg
    for f in __init__.cpython-311.pyc cupshelpers.cpython-311.pyc \
        config.cpython-311.pyc ppds.cpython-311.pyc \
        installdriver.cpython-311.pyc openprinting.cpython-311.pyc \
        xmldriverprefs.cpython-311.pyc; do
        rm -rf ${D}${PYTHON_SITEPACKAGES_DIR}/cupshelpers/__pycache__/$f
    done
}

FILES:${PN} += "\
    ${PYTHON_SITEPACKAGES_DIR}/cupshelpers \
    ${PYTHON_SITEPACKAGES_DIR}/cupshelpers/* \
    ${PYTHON_SITEPACKAGES_DIR}/cupshelpers/__pycache__/* \
    ${PYTHON_SITEPACKAGES_DIR}/cupshelpers-*.egg-info \
    ${PYTHON_SITEPACKAGES_DIR}/cupshelpers-*.egg-info/* \
    ${libdir} \
    ${datadir} \
"

RDEPENDS:${PN} = " \
    cups \
    dbus-x11 \
    gtk+3 \
    libnotify \
    python3-core \
    python3-dbus \
    python3-firewall \
    python3-pycups \
    python3-pycurl \
    python3-pygobject \
"
