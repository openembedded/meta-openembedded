SUMMARY = "GPS application for openmoko freerunner"
HOMEPAGE = "http://omgps.googlecode.com"
SECTION = "openmoko/applications"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"
DEPENDS = "gtk+ python-pygobject dbus-glib libcap"
SRCREV = "109"
PV = "0.1+svnr${SRCPV}"
PR = "r2"
S = "${WORKDIR}/${PN}"

PNBLACKLIST[omgps] ?= "BROKEN: sound.c:61:35: error: 'saveptr' may be used uninitialized in this function [-Werror=maybe-uninitialized]"

do_configure_prepend() {
    sed -i "s#PY_VERSION = 2.6#PY_VERSION = ${PYTHON_BASEVERSION}#g" ${S}/Makefile.am
    sed -i "s#PY_INC_DIR = \$(OPIEDIR)#PY_INC_DIR = ${STAGING_DIR_HOST}#g" ${S}/Makefile.am
}

SRC_URI = "svn://omgps.googlecode.com/svn/trunk;module=omgps;protocol=http \
           file://gcc-4.4.patch \
           file://sysfs.node.2.6.32.patch \
           file://fix.capability.patch \
           file://use.unused.variable.patch \
           file://fix.build.with.glib.2.34.patch \
           file://gdk-pixbuf-2.26.5.patch \
           file://0001-g_type_init-is-deprecated-for-glib-2.35.0.patch \
"

inherit autotools pkgconfig
