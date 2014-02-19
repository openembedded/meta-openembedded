SUMMARY = "FTP Server with a strong focus on software security"
DESCRIPTION = "Pure-FTPd is a free (BSD license), secure, production-quality and standard-conformant FTP server."
HOMEPAGE = "http://www.pureftpd.org/project/pure-ftpd"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=ae9e161311a5a0768c333b537a40e332"

DEPENDS = "libcap"


SRC_URI = "http://download.pureftpd.org/pub/pure-ftpd/releases/pure-ftpd-${PV}.tar.gz \
           file://0001-Remove-hardcoded-usr-local-includes-from-configure.a.patch \
           file://nostrip.patch \
"
SRC_URI[md5sum] = "bbcb48e8aa6ec1abff9775b89f84af91"
SRC_URI[sha256sum] = "90fb63b1a9d448076aa9f3e3c74b298965f98e03c824e9a4d241fffe8eb3a130"

inherit autotools

EXTRA_OECONF = "--with-minimal"
