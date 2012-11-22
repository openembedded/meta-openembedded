SUMMARY = "Network Time Protocol daemon and utilities"
DESCRIPTION = "The Network Time Protocol (NTP) is used to \
synchronize the time of a computer client or server to \
another server or reference time source, such as a radio \
or satellite receiver or modem."
HOMEPAGE = "http://support.ntp.org"
SECTION = "console/network"
LICENSE = "ntp"
LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=fea4b50c33b18c2194b4b1c9ca512670"

INC_PR = "r2"

SRC_URI = "http://www.eecis.udel.edu/~ntp/ntp_spool/ntp4/ntp-4.2/ntp-${PV}.tar.gz \
           file://tickadj.c.patch \
           file://ntp-4.2.4_p6-nano.patch \
           file://openssl-check.patch \
           file://ntpd \
           file://ntp.conf \
           file://ntpdate \
           file://ntpdate.default \
           "

inherit autotools update-rc.d

# The ac_cv_header_readline_history is to stop ntpdc depending on either
# readline or curses
EXTRA_OECONF += "--with-net-snmp-config=no --without-ntpsnmpd ac_cv_header_readline_history_h=no"
CFLAGS_append = " -DPTYS_ARE_GETPT -DPTYS_ARE_SEARCHED"

PACKAGECONFIG ??= ""
PACKAGECONFIG[openssl] = "--with-openssl-libdir=${STAGING_LIBDIR} \
                          --with-openssl-incdir=${STAGING_INCDIR} \
                          --with-crypto, \
                          --without-openssl --without-crypto, \
                          openssl"

do_install_append() {
	install -d ${D}${sysconfdir}/init.d
	install -m 644 ${WORKDIR}/ntp.conf ${D}${sysconfdir}
	install -m 755 ${WORKDIR}/ntpd ${D}${sysconfdir}/init.d
	install -d ${D}${bindir}
	install -m 755 ${WORKDIR}/ntpdate ${D}${bindir}/ntpdate-sync

	# Fix hardcoded paths in scripts
	sed -i 's!/usr/sbin/!${sbindir}/!g' ${D}${sysconfdir}/init.d/ntpd ${D}${bindir}/ntpdate-sync
	sed -i 's!/usr/bin/!${bindir}/!g' ${D}${sysconfdir}/init.d/ntpd ${D}${bindir}/ntpdate-sync
	sed -i 's!/etc/!${sysconfdir}/!g' ${D}${sysconfdir}/init.d/ntpd ${D}${bindir}/ntpdate-sync
	sed -i 's!/var/!${localstatedir}/!g' ${D}${sysconfdir}/init.d/ntpd ${D}${bindir}/ntpdate-sync
	sed -i 's!^PATH=.*!PATH=${base_sbindir}:${base_bindir}:${sbindir}:${bindir}!' ${D}${bindir}/ntpdate-sync

	install -d ${D}/${sysconfdir}/default
	install -m 644 ${WORKDIR}/ntpdate.default ${D}${sysconfdir}/default/ntpdate
	install -d ${D}/${sysconfdir}/network/if-up.d
	ln -s ${bindir}/ntpdate-sync ${D}/${sysconfdir}/network/if-up.d
}

PACKAGES += "ntpdate ${PN}-tickadj ${PN}-utils"
# NOTE: you don't need ntpdate, use "ntpd -q -g -x"

# ntp originally includes tickadj. It's split off for inclusion in small firmware images on platforms
# with wonky clocks (e.g. OpenSlug)
RDEPENDS_${PN} = "${PN}-tickadj"
# Handle move from bin to utils package
RPROVIDES_${PN}-utils = "${PN}-bin"
RREPLACES_${PN}-utils = "${PN}-bin"
RCONFLICTS_${PN}-utils = "${PN}-bin"

RSUGGESTS_${PN} = "iana-etc"

FILES_${PN} = "${bindir}/ntpd ${sysconfdir}/ntp.conf ${sysconfdir}/init.d/ntpd ${sbindir} ${libdir}"
FILES_${PN}-tickadj = "${bindir}/tickadj"
FILES_${PN}-utils = "${bindir}"
FILES_ntpdate = "${bindir}/ntpdate ${sysconfdir}/network/if-up.d/ntpdate-sync ${bindir}/ntpdate-sync ${sysconfdir}/default/ntpdate"

CONFFILES_${PN} = "${sysconfdir}/ntp.conf"
CONFFILES_ntpdate = "${sysconfdir}/default/ntpdate"

INITSCRIPT_NAME = "ntpd"
# No dependencies, so just go in at the standard level (20)
INITSCRIPT_PARAMS = "defaults"

pkg_postinst_ntpdate() {
if test "x$D" != "x"; then
        exit 1
else
        if ! grep -q -s ntpdate /var/spool/cron/root; then
                echo "adding crontab"
                test -d /var/spool/cron || mkdir -p /var/spool/cron
                echo "30 * * * *    ${bindir}/ntpdate-sync silent" >> /var/spool/cron/root
        fi
fi
}

