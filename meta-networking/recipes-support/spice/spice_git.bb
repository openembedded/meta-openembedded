#
# Copyright (C) 2013 Wind River Systems, Inc.
#

SUMMARY = "Simple Protocol for Independent Computing Environments"
DESCRIPTION = "SPICE (the Simple Protocol for Independent Computing \
Environments) is a remote-display system built for virtual \
environments which allows users to view a computing 'desktop' \ 
environment - not only on its computer-server machine, but also from \
anywhere on the Internet and using a wide variety of machine \
architectures."

LICENSE = "BSD & LGPLv2.1+"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

PV = "0.12.4+git${SRCPV}"

# Actual versions based on the checkouts below
# spice = "0.12.4"
# common = "0.12.6"
# protocol = "0.12.6"
SRCREV_spice = "b270fb010a3ddb432dfe6b15e4bdffa6ac086cd0"
SRCREV_spice-common = "fe93908238196bd632287fc9875e6f2e11105d04"
SRCREV_spice-protocol = "784407f248e7f99d2bfcc9368f9acd1efb2b9617"

SRCREV_FORMAT = "spice_spice-common_spice-protocol"

SRC_URI = " \
    git://anongit.freedesktop.org/spice/spice;name=spice \
    git://anongit.freedesktop.org/spice/spice-common;destsuffix=git/spice-common;name=spice-common \
    git://anongit.freedesktop.org/spice/spice-protocol;destsuffix=git/spice-common/spice-protocol;name=spice-protocol \
"

SRC_URI += " \
    file://spice-fix-CVE-2013-4282.patch \
    file://configure.ac-add-subdir-objects-to-AM_INIT_AUTOMAKE.patch \
    file://build-allow-separated-src-and-build-dirs.patch \
    file://0001-red_parse_qxl-Fix-BITMAP_FMT_IS_RGB-defined-but-not-.patch \
    file://0001-Use-PRI-macros-in-printf-to-keep-compatibility-betwe.patch \
    file://Fix-build-issues-with-gcc-7.patch \
    file://0001-spice-compile-warnings.m4-don-t-define-FORITFY_SOURC.patch \
"

S = "${WORKDIR}/git"

inherit autotools gettext pythonnative python-dir pkgconfig

DEPENDS += "celt051 jpeg pixman alsa-lib glib-2.0 python-pyparsing-native"
DEPENDS_append_class-nativesdk = "nativesdk-openssl"

export PYTHON="${STAGING_BINDIR_NATIVE}/python-native/python"
export PYTHONPATH="${PKG_CONFIG_SYSROOT_DIR}${libdir}/python2.7/site-packages"

PACKAGECONFIG_class-native = ""
PACKAGECONFIG_class-nativesdk = ""
PACKAGECONFIG ?= "sasl"

PACKAGECONFIG[smartcard] = "--enable-smartcard,--disable-smartcard,libcacard,"
PACKAGECONFIG[sasl] = "--with-sasl,--without-sasl,cyrus-sasl,"
PACKAGECONFIG[client] = "--enable-client,--disable-client,,"
PACKAGECONFIG[gui] = "--enable-gui,--disable-gui,,"
PACKAGECONFIG[opengl] = "--enable-opengl,--disable-opengl,,"
PACKAGECONFIG[xinerama] = "--enable-xinerama,--disable-xinerama,libxinerama,"

PACKAGES =+ "${PN}-protocol"
LICENSE_${PN}-protocol = "BSD"
FILES_${PN}-protocol += "${includedir}/spice-1"
FILES_${PN}-protocol += "${datadir}/pkgconfig"

do_configure_prepend() {
    mkdir -p ${S}/spice-common/spice-protocol/m4
}

do_install_append() {
    cd ${B}/spice-common/spice-protocol
    oe_runmake DESTDIR="${D}" install
    cd -
}

COMPATIBLE_HOST = '(x86_64|i.86).*-linux'

BBCLASSEXTEND = "native nativesdk"
