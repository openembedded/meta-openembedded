DESCRIPTION = "The GNU inetutils are a collection of common \
networking utilities and servers including ftp, ftpd, rcp, \
rexec, rlogin, rlogind, rsh, rshd, syslog, syslogd, talk, \
talkd, telnet, telnetd, tftp, tftpd, and uucpd."
SECTION = "libs"
DEPENDS = "ncurses"
LICENSE = "GPLv3"

LIC_FILES_CHKSUM = "file://COPYING;md5=0c7051aef9219dc7237f206c5c4179a7"


SRC_URI = "${GNU_MIRROR}/inetutils/inetutils-${PV}.tar.gz \
           file://disable-pre-ANSI-compilers.patch \
           file://version.patch \
           file://remove-gets.patch \
           file://inetutils-1.8-0001-printf-parse-pull-in-features.h-for-__GLIBC__.patch \
           file://inetutils-1.8-0003-wchar.patch \
           file://fix-disable-ipv6.patch \
" 
SRC_URI[md5sum] = "944f7196a2b3dba2d400e9088576000c"
SRC_URI[sha256sum] = "02a9ebde8a198cb85f87545b9d88fb103a183958139864a85fe9e027ad79ff2b"

inherit autotools gettext

noipv6="${@base_contains('DISTRO_FEATURES', 'ipv6', '', '--disable-ipv6 gl_cv_socket_ipv6=no', d)}"
EXTRA_OECONF = "--with-ncurses-include-dir=${STAGING_INCDIR} \
		--with-path-procnet-dev=/proc/net/dev \
		${noipv6} \
		"

do_configure_prepend () {
	export HELP2MAN='true'
	cp ${STAGING_DATADIR_NATIVE}/gettext/config.rpath ${S}/build-aux/config.rpath
	rm -f ${S}/glob/configure*
}

do_install () {
	autotools_do_install
	install -d ${D}${base_sbindir} ${D}${base_bindir}
	mv ${D}${bindir}/tftp ${D}${bindir}/tftp.${PN}
	mv ${D}${bindir}/telnet ${D}${bindir}/telnet.${PN}
	mv ${D}${bindir}/logger ${D}${bindir}/logger.${PN}
	mv ${D}${bindir}/traceroute ${D}${bindir}/traceroute.${PN}
	mv ${D}${bindir}/hostname ${D}${base_bindir}/hostname.${PN}
	mv ${D}${bindir}/ifconfig ${D}${base_sbindir}/ifconfig.${PN}
}

pkg_postinst_${PN} () {
	update-alternatives --install ${bindir}/tftp tftp tftp.${PN} 100
	update-alternatives --install ${bindir}/telnet telnet telnet.${PN} 100
	update-alternatives --install ${bindir}/logger logger logger.${PN} 100
	update-alternatives --install ${bindir}/traceroute traceroute traceroute.${PN} 100
	update-alternatives --install ${base_bindir}/hostname hostname hostname.${PN} 100
	update-alternatives --install ${base_sbindir}/ifconfig ifconfig ifconfig.${PN} 100
}

pkg_prerm_${PN} () {
	update-alternatives --remove tftp tftp.${PN}
	update-alternatives --remove telnet telnet.${PN}
	update-alternatives --remove logger logger.${PN}
	update-alternatives --remove traceroute traceroute.${PN}
	update-alternatives --remove ifconfig ifconfig.${PN}
}
