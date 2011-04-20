DESCRIPTION = "Alternative system logger daemon"
DEPENDS = "libol flex eventlog glib-2.0"
LICENSE = "GPL LGPL"
LIC_FILES_CHKSUM = "file://COPYING;md5=7ec1bcc46f28b11f4722e20d9b7dd4d5"

# syslog initscript is handled explicitly because order of
# update-rc.d and update-alternatives is important
RDEPENDS_${PN} += " ${@base_conditional("ONLINE_PACKAGE_MANAGEMENT", "none", "", "update-rc.d", d)}"

INC_PR = "r6"

inherit autotools

SRC_URI = "http://www.balabit.com/downloads/files/syslog-ng/sources/${PV}/source/${PN}_${PV}.tar.gz"

noipv6 = "${@base_contains('DISTRO_FEATURES', 'ipv6', '', '--disable-ipv6', d)}"

EXTRA_OECONF = " \
  --with-libnet=${STAGING_BINDIR_CROSS} \
  --enable-dynamic-linking \
  ${noipv6} \
  --enable-ssl \
  --disable-sub-streams \
  --disable-pacct \
  --disable-linux-caps \
  --disable-pcre \
  --disable-sql \
"

do_configure_prepend() {
	eval "${@base_contains('DISTRO_FEATURES', 'largefile', '', 'sed -i -e "s/-D_LARGEFILE_SOURCE//" -e "s/-D_FILE_OFFSET_BITS=64//" ${S}/configure.in', d)}"
}

do_install_append() {
        install -d ${D}/${sysconfdir}/${PN}
        install ${WORKDIR}/syslog-ng.conf ${D}${sysconfdir}/${PN}.conf
        install -d ${D}/${sysconfdir}/init.d
        install -m 755 ${WORKDIR}/initscript ${D}/${sysconfdir}/init.d/syslog.${PN}
}

CONFFILES_${PN} = "${sysconfdir}/${PN}.conf"

pkg_postinst_${PN} () {
        /etc/init.d/syslog stop
        update-alternatives --install ${sysconfdir}/init.d/syslog syslog-init syslog.${PN} 200

        if test "x$D" != "x"; then
                OPT="-r $D"
        else
                OPT="-s"
        fi
        # remove all rc.d-links potentially created from alternative
        # syslog packages before creating new ones
        update-rc.d $OPT -f syslog remove
        update-rc.d $OPT syslog start 20 2 3 4 5 . stop 90 0 1 6 .
}

pkg_prerm_${PN} () {
        if test "x$D" = "x"; then
                if test "$1" = "upgrade" -o "$1" = "remove"; then
                        /etc/init.d/syslog stop
                fi
        fi

        update-alternatives --remove syslog-init syslog.${PN}
}

pkg_postrm_${PN} () {
        if test "x$D" != "x"; then
                OPT="-r $D"
        else
                OPT=""
        fi
        if test "$1" = "remove" -o "$1" = "purge"; then
                if ! test -e "/etc/init.d/syslog"; then
                        update-rc.d $OPT syslog remove
                fi
        fi
}
