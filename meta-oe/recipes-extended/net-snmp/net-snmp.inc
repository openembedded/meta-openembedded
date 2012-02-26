DESCRIPTION = "Various tools relating to the Simple Network Management Protocol"
HOMEPAGE = "http://www.net-snmp.org/"
LICENSE = "BSD"

DEPENDS = "openssl libnl"
RDEPENDS_${PN}-server += "net-snmp-mibs"
RDEPENDS_${PN}-client += "net-snmp-mibs"
RDEPENDS_${PN}-dev = "net-snmp-client (= ${EXTENDPV}) net-snmp-server (= ${EXTENDPV})"
RRECOMMENDS_${PN}-dbg = "net-snmp-client (= ${EXTENDPV}) net-snmp-server (= ${EXTENDPV})"
INC_PR = "r2"

inherit autotools update-rc.d siteinfo

TARGET_CC_ARCH += "${LDFLAGS}"

EXTRA_OECONF = "--enable-shared --disable-manuals --with-defaults \
${@base_conditional('SITEINFO_ENDIANNESS', 'le', '--with-endianness=little', '--with-endianness=big', d)}"

do_install () {
	oe_runmake install
}

do_install_append() {
	install -d ${D}${sysconfdir}/snmp
	install -d ${D}${sysconfdir}/init.d
	install -m 755 ${WORKDIR}/init ${D}${sysconfdir}/init.d/snmpd
	install -m 644 ${WORKDIR}/snmpd.conf ${D}${sysconfdir}/snmp/
	install -m 644 ${WORKDIR}/snmptrapd.conf ${D}${sysconfdir}/snmp/
	install -d ${STAGING_BINDIR}
	install -m 0755 ${D}${bindir}/net-snmp-config ${STAGING_BINDIR}/
	sed -e "s@-I/usr/include@@g" \
		-e "s@^prefix=.*@prefix=${STAGING_DIR_HOST}@g" \
		-e "s@^exec_prefix=.*@exec_prefix=${STAGING_DIR_HOST}@g" \
		-e "s@^includedir=.*@includedir=${STAGING_INCDIR}@g" \
		-e "s@^libdir=.*@libdir=${STAGING_LIBDIR}@g" \
		-i ${STAGING_BINDIR}/net-snmp-config
}

PACKAGES = "net-snmp-dbg net-snmp-doc net-snmp-dev net-snmp-staticdev net-snmp-static net-snmp-libs \
            net-snmp-mibs net-snmp-server net-snmp-client"

FILES_${PN}-libs = "${libdir}/lib*${SOLIBS}"
FILES_${PN}-mibs = "${datadir}/snmp/mibs"
FILES_${PN}-server = "${sbindir}/* ${sysconfdir}"
FILES_${PN}-client = "${bindir}/* ${datadir}/snmp/"
FILES_${PN}-dbg += "${libdir}/.debug/ ${sbindir}/.debug/ ${bindir}/.debug/"
FILES_${PN}-dev += "${bindir}/net-snmp-config ${bindir}/mib2c ${bindir}/mib2c-update"

CONFFILES_${PN}-server = "${sysconfdir}/snmp/snmpd.conf \
                          ${sysconfdir}/snmp/snmptrapd.conf"

INITSCRIPT_PACKAGES = "${PN}-server"
INITSCRIPT_NAME_${PN}-server = "snmpd"
INITSCRIPT_PARAMS_${PN}-server = "defaults"

LEAD_SONAME = "libnetsnmp.so"

pkg_postrm_net-snmp-server() {
	if test "x$D" != "x"; then
		OPT="-r $D "
	else
		OPT=""
		/etc/init.d/snmpd stop
	fi
}
