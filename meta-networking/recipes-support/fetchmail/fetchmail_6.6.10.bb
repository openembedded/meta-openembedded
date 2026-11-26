SUMMARY = "Fetchmail retrieves mail from remote mail servers and forwards it via SMTP"
HOMEPAGE = "http://www.fetchmail.info/"
DESCRIPTION = "Fetchmail is a full-featured, robust, well-documented remote-mail retrieval \
and forwarding utility intended to be used over on-demand TCP/IP links (such as SLIP or PPP \
connections). It supports every remote-mail protocol now in use on the Internet: POP2, POP3, \
RPOP, APOP, KPOP, all flavors of IMAP, ETRN, and ODMR. It can even support IPv6 and IPSEC."
SECTION = "mail"
LICENSE = "GPL-2.0-only AND MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=1d97f83179a23f8039e1de1420382e4d"

DEPENDS = "openssl"

SRC_URI = "${SOURCEFORGE_MIRROR}/${BPN}/${BPN}-${PV}.tar.xz \
           "
SRC_URI[sha256sum] = "f32f1ecfdb99a243b6f7fef5d1b5f5aeac2bb0a45ce0162552d6063db3b48073"

UPSTREAM_CHECK_URI = "https://sourceforge.net/projects/fetchmail/files/branch_6.6/"
UPSTREAM_CHECK_REGEX = "fetchmail-(?P<pver>\d+(\.\d+)+)\.tar\.xz"

inherit autotools gettext pkgconfig python3-dir python3native

EXTRA_OECONF = "--with-ssl=${STAGING_DIR_HOST}${prefix} --disable-rpath "

do_install:append() {
    sed -i 's,${RECIPE_SYSROOT_NATIVE},,g' ${D}${bindir}/fetchmailconf
}

PACKAGES =+ "fetchmail-python"
FILES:fetchmail-python = "${libdir}/${PYTHON_DIR}/*"
