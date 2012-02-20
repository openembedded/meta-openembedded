DESCRIPTION = "Secure ftp server"
HOMEPAGE="https://security.appspot.com/vsftpd.html"
SECTION = "network"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=a6067ad950b28336613aed9dd47b1271"

DEPENDS = "libcap"

SRC_URI = "https://security.appspot.com/downloads/vsftpd-2.3.5.tar.gz \
           file://makefile.patch \
           file://nopam.patch \
           file://init \
           file://vsftpd.conf"

inherit update-rc.d useradd

CONFFILES_${PN} = "${sysconfdir}/vsftpd.conf"
LDFLAGS_append =" -lcrypt -lcap"

do_configure() {
        # Fix hardcoded /usr, /etc, /var mess.
        cat tunables.c|sed s:\"/usr:\"${prefix}:g|sed s:\"/var:\"${localstatedir}:g \
        |sed s:\"${prefix}/share/empty:\"${localstatedir}/share/empty:g |sed s:\"/etc:\"${sysconfdir}:g > tunables.c.new
        mv tunables.c.new tunables.c
}

do_install() {
        install -d ${D}${sbindir}
        install -d ${D}${mandir}/man8
        install -d ${D}${mandir}/man5
        oe_runmake 'DESTDIR=${D}' install
        install -d ${D}${sysconfdir}
        install -m 0755 ${WORKDIR}/vsftpd.conf ${D}${sysconfdir}/vsftpd.conf
        install -d ${D}${sysconfdir}/init.d/
        install -m 755 ${WORKDIR}/init ${D}${sysconfdir}/init.d/vsftpd
}

INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME_${PN} = "vsftpd"
INITSCRIPT_PARAMS_${PN} = "defaults 80"

USERADD_PACKAGES = "${PN}"
USERADD_PARAM_${PN} = "--system --home-dir /var/lib/ftp --no-create-home -g ftp \
                       --shell /bin/false ftp "
GROUPADD_PARAM_${PN} = "-r ftp"
                     
SRC_URI[md5sum] = "01398a5bef8e85b6cf2c213a4b011eca"
SRC_URI[sha256sum] = "d87ee2987df8f03e1dbe294905f7907b2798deb89c67ca965f6e2f60879e54f1"
