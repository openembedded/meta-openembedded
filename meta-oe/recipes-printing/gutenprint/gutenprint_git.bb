# Recipe for building gutenprint
#
# Uses the gutenprint-native extracted strings and disable local build stuff
#
# Copyright (c) Ambu A/S - All rights reserved
# SPDX-License-Identifier: MIT
#
# Author(s)
#   clst@ambu.com (Claus Stovgaard)
#

DESCRIPTION = "Gutenprint printer drivers"
HOMEPAGE = "http://gimp-print.sourceforge.net/"
LICENSE = "GPL-2.0-or-later"

SRC_URI = "git://git.code.sf.net/p/gimp-print/source;protocol=https;branch=master"
SRCREV = "66b0a7bc3fd25659a3f295db0ebb39d04e413c01"
PV = "5.3.3+git${SRCPV}"

S = "${WORKDIR}/git"

LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"

inherit autotools gettext pkgconfig

DEPENDS += "glib-2.0-native cups gutenprint-native"
# autogen.sh needs autopoint
DEPENDS:class-native = "glib-2.0-native gettext-native"

EXTRA_OECONF = "--without-doc --disable-test PERL=/usr/bin/perl"
EXTRA_OECONF:append:class-native = " --without-cups"

do_configure:prepend:class-target() {
    # Disable the xmli18n-tmp.h rule
    # It depend on the local build extract-strings, we are not able to run this
    # So we are using the xmli18n-tmp.h created by gutenprint-native
    sed -i 's/all-local: xmli18n-tmp.h xml-stamp/all-local: xml-stamp/'  ${S}/src/xml/Makefile.am
    sed -i 's/dist-hook: xmli18n-tmp.h xml-stamp/dist-hook: xml-stamp/'  ${S}/src/xml/Makefile.am
    # Despite being a generated file, this needs to be in S.
    cp ${STAGING_DATADIR_NATIVE}/gutenprint/xmli18n-tmp.h ${S}/src/xml/
}

do_configure() {
    # Need to call autogen.sh as that creates m4/stp_release.m4
    cd ${S}
    NOCONFIGURE=1 ./autogen.sh
    cd ${B}
    oe_runconf
}

do_install:append() {
    # This file contains build paths and isn't very useful, remove it
    rm -f ${D}${libdir}/gutenprint/*/config.summary
    # Match ownership to cups
    chgrp lp ${D}${sysconfdir}/cups
}

do_compile:class-native() {
    oe_runmake -C ${B}/src/xml
}

do_install:class-native() {
    install -d ${D}${datadir}/gutenprint/
    install -m644 ${B}/src/xml/xmli18n-tmp.h ${D}${datadir}/gutenprint/
}

FILES:${PN} += "${datadir}/cups/*"
RDEPENDS:${PN} = "perl"

BBCLASSEXTEND = "native"

# Pull in base-passwd for the lp user
DEPENDS:append:class-target = " base-passwd"
PACKAGE_WRITE_DEPS += "base-passwd"
