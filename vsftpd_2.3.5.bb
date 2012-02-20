DESCRIPTION = "lightweight, efficient FTP server written for security"
HOMEPAGE = "https://security.appspot.com/vsftpd.html"
SECTION = "console/network"
LICENSE = "GPL-2.0-with-OpenSSL-exception"
LIC_FILES_CHKSUM = "file://COPYING;md5=a6067ad950b28336613aed9dd47b1271"
DEPENDS = "libcap openssl"
DEPENDS += "${@base_contains('DISTRO_FEATURES', 'pam', 'libpam', '', d)}"

SRC_URI = " \
        https://security.appspot.com/downloads/${BP}.tar.gz \
        file://vsftpd.xinetd.in \
        file://01-builddefs.patch \
        file://02-config.patch \
        file://03-db-doc.patch \
        file://04-link-local.patch \
        file://05-whitespaces.patch \
        file://06-greedy.patch \
        file://07-utf8.patch \
        file://08-manpage.patch \
        file://09-s390.patch \
        file://10-remote-dos.patch \
"
SRC_URI[md5sum] = "01398a5bef8e85b6cf2c213a4b011eca"
SRC_URI[sha256sum] = "d87ee2987df8f03e1dbe294905f7907b2798deb89c67ca965f6e2f60879e54f1"

S = "${WORKDIR}/${BP}"

inherit useradd

CFLAGS = "${TARGET_CFLAGS}"
CFLAGS += "-DVSF_BUILD_SSL=1"
CFLAGS += "${@base_contains('DISTRO_FEATURES', 'pam', '-DVSF_BUILD_PAM=1', '', d)}"

LIBS = "-lcap -lcrypt -lssl -lcrypto"
LIBS += "${@base_contains('DISTRO_FEATURES', 'pam', '-lpam', '', d)}"

LINK = "${TARGET_LDFLAGS}"

SECURE_CHROOT_DIR = "${datadir}/${BPN}/chroot"
RSA_CERT_FILE = "${sysconfdir}/ssl/private/${BPN}.pem"

do_configure() {
        rm -f builddefs.h
        touch builddefs.h
        set_default() {
                NAME=$1
                VALUE=$2
                sed -e "s,^#\?${NAME}=.*,${NAME}=${VALUE}," -i vsftpd.conf
        }
        set_default listen NO
        set_default listen_ipv6 NO
        set_default anonymous_enable NO
        set_default local_enable YES
        set_default write_enable YES
        set_default anon_upload_enable NO
        set_default anon_mkdir_write_enable NO
        set_default dirmessage_enable NO
        set_default use_localtime YES
        set_default xferlog_enable NO
        set_default connect_from_port_20 YES
        set_default chown_uploads NO
        set_default nopriv_user vsftpd
        set_default async_abor_enable YES
        set_default ascii_upload_enable NO
        set_default ascii_download_enable NO
        set_default ftpd_banner "Welcome to the ${DISTRO_NAME} FTP service!"
        set_default chroot_local_user NO
        set_default chroot_list_enable NO
        set_default ls_recurse_enable YES
        set_default secure_chroot_dir "${SECURE_CHROOT_DIR}"
        set_default rsa_cert_file "${RSA_CERT_FILE}"

	sed -e 's,@SBINDIR@,${sbindir},' ${WORKDIR}/vsftpd.xinetd.in > vsftpd.xinetd
}
do_compile() {
        oe_runmake 'CFLAGS=${CFLAGS}' 'LIBS=${LIBS}' 'LINK=${LINK}'
}
do_install() {
        install -d ${D}${sysconfdir}
        install -m 644 vsftpd.conf ${D}${sysconfdir}
        install -d ${D}${sbindir}
        install -m 755 vsftpd ${D}${sbindir}/vsftpd
        install -d ${D}${mandir}/man8
        install -m 644 vsftpd.8 ${D}${mandir}/man8/vsftpd.8
        install -d ${D}${mandir}/man5
        install -m 644 vsftpd.conf.5 ${D}${mandir}/man5/vsftpd.conf.5
        install -d ${D}${sysconfdir}/xinetd.d
        install -m 644 vsftpd.xinetd ${D}${sysconfdir}/xinetd.d/vsftpd
        install -d ${D}${SECURE_CHROOT_DIR}
}

RDEPENDS_${PN} = "xinetd"

USERADD_PACKAGES = "${PN}"
USERADD_PARAM_${PN} = "--home-dir ${SECURE_CHROOT_DIR} --no-create-home --system --shell /bin/false --user-group vsftpd"
