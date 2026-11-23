# SPDX-License-Identifier: MIT
#
# Copyright Leica Geosystems AG
#

require recipes-core/systemd/systemd.inc

SUMMARY = "systemd-repart"
DESCRIPTION = "systemd-repart grows and adds partitions to a partition table, based on the configuration files described in repart.d(5), or generates a Discoverable Disk Image (DDI) for a system extension (sysext, see systemd-sysext(8))."
HOMEPAGE = "http://www.freedesktop.org/wiki/Software/systemd"

DEPENDS = " \
    cryptsetup-native \
    gperf-native \
    libcap-native \
    python3-jinja2-native \
    util-linux-native \
"

inherit meson pkgconfig gettext native

MESON_TARGET = "systemd-repart"

SYSTEMD_MAJOR = "${@d.getVar('PV').split('.')[0]}"

EXTRA_OEMESON += "-Dnobody-user=nobody \
    -Dnobody-group=nogroup \
    -Ddefault-locale=C \
    -Dmode=release \
    -Dsystem-alloc-uid-min=101 \
    -Dsystem-uid-max=999 \
    -Dsystem-alloc-gid-min=101 \
    -Dsystem-gid-max=999 \
"

do_install() {
    install -d ${D}${bindir}/
    install -m 0755 ${B}/systemd-repart ${D}${bindir}/systemd-repart
    install -d ${D}${libdir}/
    install -m 0644 ${B}/src/shared/libsystemd-shared-${SYSTEMD_MAJOR}.so ${D}${libdir}/libsystemd-shared-${SYSTEMD_MAJOR}.so

    install -d ${D}${libdir}/systemd/repart/
    cp -r ${S}/src/repart/definitions ${D}${libdir}/systemd/repart/
}
