SUMMARY = "Secure and configurable FTP server"
SECTION = "console/network"
HOMEPAGE = "http://www.proftpd.org"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=fb0d1484d11915fa88a6a7702f1dc184"

PR = "r4"

SRC_URI = "ftp://ftp.proftpd.org/distrib/source/${BPN}-${PV}.tar.gz \
           file://make.patch \
           file://basic.conf.patch \
           file://contrib.patch \
           file://proftpd-basic.init \
           file://default \
           file://move-pidfile-to-var-run.patch \
           file://close-RequireValidShell-check.patch \
           file://move-runfile-to-var-run.patch \
           file://proftpd-sftp.patch \
"

SRC_URI[md5sum] = "0871e0b93c9c3c88ca950b6d9a04aed2"
SRC_URI[sha256sum] = "9f659585cea90fc6af34a0ffae4a90e4ed37abe92dbd9b6c311f95a436c961cb"

inherit autotools-brokensep useradd update-rc.d

PACKAGECONFIG ??= ""
PACKAGECONFIG[curses] = "--enable-curses --enable-ncurses,--disable-curses --disable-ncurses,ncurses"

EXTRA_OECONF = "--disable-cap \
                --disable-auth-pam \
"

# proftpd uses libltdl which currently makes configuring using
# autotools.bbclass a pain...
do_configure () {
    oe_runconf
}

FTPUSER = "ftp"
FTPGROUP = "ftp"

do_install () {
    oe_runmake DESTDIR=${D} install
    rmdir ${D}${libdir}/proftpd ${D}${datadir}/locale
    [ -d ${D}${libexecdir} ] && rmdir ${D}${libexecdir}
    sed -i '/ *User[ \t]*/s/ftp/${FTPUSER}/' ${D}${sysconfdir}/proftpd.conf
    sed -i '/ *Group[ \t]*/s/ftp/${FTPGROUP}/' ${D}${sysconfdir}/proftpd.conf
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/proftpd-basic.init ${D}${sysconfdir}/init.d/proftpd
    sed -i 's!/usr/sbin/!${sbindir}/!g' ${D}${sysconfdir}/init.d/proftpd
    sed -i 's!/etc/!${sysconfdir}/!g' ${D}${sysconfdir}/init.d/proftpd
    sed -i 's!/var/!${localstatedir}/!g' ${D}${sysconfdir}/init.d/proftpd
    sed -i 's!^PATH=.*!PATH=${base_sbindir}:${base_bindir}:${sbindir}:${bindir}!' ${D}${sysconfdir}/init.d/proftpd

    install -d ${D}${sysconfdir}/default
    install -m 0755 ${WORKDIR}/default ${D}${sysconfdir}/default/proftpd

    # create the pub directory
    mkdir -p ${D}/home/${FTPUSER}/pub/
    chown -R ${FTPUSER}:${FTPGROUP} ${D}/home/${FTPUSER}/pub
}

INITSCRIPT_NAME = "proftpd"
INITSCRIPT_PARAM = "defaults 85 15"

USERADD_PACKAGES = "${PN}"
GROUPADD_PARAM_${PN} = "--system ${FTPGROUP}"
USERADD_PARAM_${PN} = "--system -g ${FTPGROUP} --home-dir /var/lib/${FTPUSER} --no-create-home \
                       --shell /bin/false ${FTPUSER}"

FILES_${PN} += "/home/${FTPUSER}"

RDEPENDS_${PN} += "perl"
