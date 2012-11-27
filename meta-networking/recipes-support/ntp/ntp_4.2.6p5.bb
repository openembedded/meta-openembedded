require ntp.inc

SRC_URI = "http://www.eecis.udel.edu/~ntp/ntp_spool/ntp4/ntp-4.2/ntp-${PV}.tar.gz \
        file://tickadj.c.patch \
        file://ntp-4.2.4_p6-nano.patch \
        file://ntpd \
        file://ntp.conf \
        file://ntpdate \
"

SRC_URI[md5sum] = "00df80a84ec9528fcfb09498075525bc"
SRC_URI[sha256sum] = "d6ab8371f9d31e594eb6922823d5ccd03dcc4e9d84b0e23ea25ac1405432f91c"

EXTRA_OECONF += " --with-net-snmp-config=no --without-ntpsnmpd" 

do_install_append() {
	install -d ${D}/${sysconfdir}/init.d
	install -m 644 ${WORKDIR}/ntp.conf ${D}/${sysconfdir}
	install -m 755 ${WORKDIR}/ntpd ${D}/${sysconfdir}/init.d
	install -d ${D}/${sysconfdir}/network/if-up.d
	install -m 755 ${WORKDIR}/ntpdate ${D}/${sysconfdir}/network/if-up.d
}

FILES_${PN}-bin = "${bindir}/ntp-wait ${bindir}/ntpdc ${bindir}/ntpq ${bindir}/ntptime ${bindir}/ntptrace"
FILES_${PN} = "${bindir}/ntpd ${sysconfdir}/ntp.conf ${sysconfdir}/init.d/ntpd"
FILES_${PN}-tickadj = "${bindir}/tickadj"
FILES_ntp-utils = "${bindir}/*"
FILES_ntpdate = "${bindir}/ntpdate ${sysconfdir}/network/if-up.d/ntpdate"

# ntp originally includes tickadj. It's split off for inclusion in small firmware images on platforms
# with wonky clocks (e.g. OpenSlug)
RDEPENDS_${PN} = "${PN}-tickadj"

pkg_postinst_ntpdate() {
if test "x$D" != "x"; then
        exit 1
else
        if ! grep -q -s ntpdate /var/spool/cron/root; then
                echo "adding crontab"
                test -d /var/spool/cron || mkdir -p /var/spool/cron
                echo "30 * * * *    /usr/bin/ntpdate -b -s -u pool.ntp.org" >> /var/spool/cron/root
        fi
fi
}

