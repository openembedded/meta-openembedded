SUMMARY = "ISC Kea DHCP Server"
DESCRIPTION = "Kea is the next generation of DHCP software developed by ISC. It supports both DHCPv4 and DHCPv6 protocols along with their extensions, e.g. prefix delegation and dynamic updates to DNS."
HOMEPAGE = "http://kea.isc.org"
SECTION = "connectivity"
LICENSE = "MPL-2.0 & BSL-1.0 & Apache-2.0"
LIC_FILES_CHKSUM = "\
    file://COPYING;md5=9310b19bc010f85f4ad19637f168f23f \
    file://ext/coroutine/LICENSE_1_0.txt;md5=e4224ccaecb14d942c71d31bef20d78c \
"

SRC_URI = "\
    http://ftp.isc.org/isc/kea/${PV}/${BP}.tar.gz \
    file://0001-Added-option-to-provide-kea-msg-compiler.patch \
    file://0001-Makefile.am-fix-compile-error-about-kea-msg-compiler.patch \
    file://0001-asiolink-fix-build-with-boost-1.66.patch \
    file://0001-Makefile.am-update-hooksdir-for-lease_cmds.patch \
    file://kea-dhcp4.service \
    file://kea-dhcp6.service \
    file://kea-dhcp-ddns.service \
"

SRC_URI[md5sum] = "f93bf836fd555a221f9ed0d4520058db"
SRC_URI[sha256sum] = "6edfcdbf2526c218426a1d1a6a6694a4050c97bb8412953a230285d63415c391"

inherit autotools systemd

SYSTEMD_SERVICE_${PN} = "kea-dhcp4.service kea-dhcp6.service kea-dhcp-ddns.service"
SYSTEMD_AUTO_ENABLE = "disable"

do_install_append_class-target() {
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/kea-dhcp*service ${D}${systemd_system_unitdir}
    sed -i -e 's,@SBINDIR@,${sbindir},g' -e 's,@BASE_BINDIR@,${base_bindir},g' \
           -e 's,@LOCALSTATEDIR@,${localstatedir},g' -e 's,@SYSCONFDIR@,${sysconfdir},g' \
           ${D}${systemd_system_unitdir}/kea-dhcp*service
}

do_install_append() {
    rm -rf "${D}${localstatedir}"
}

PACKAGECONFIG_class-target ??= "kmc openssl log4cplus boost"
PACKAGECONFIG_class-native ??= "openssl log4cplus boost"

PACKAGECONFIG[kmc] = "--with-msg-compiler=${STAGING_BINDIR_NATIVE}/kea-msg-compiler,,kea-native,"
PACKAGECONFIG[openssl] = "--with-openssl=${STAGING_DIR_TARGET}${prefix},,openssl,openssl"
PACKAGECONFIG[log4cplus] = "--with-log4cplus=${STAGING_DIR_TARGET}${prefix},,log4cplus,log4cplus"
PACKAGECONFIG[boost] = "--with-boost-include=${STAGING_INCDIR} --with-boost-lib-dir=${STAGING_LIBDIR} --with-boost-libs=-lboost_system,,boost,boost"

FILES_${PN}-staticdev += "${libdir}/kea/hooks/*.a"

BBCLASSEXTEND += "native"
