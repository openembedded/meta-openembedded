SUMMARY = "mcelog daemon accounts memory and some other errors in various ways."
DESCRIPTION = "mcelog is required by both 32bit x86 Linux kernels (since 2.6.30) \
and 64bit Linux kernels (since early 2.6 kernel releases) to log machine checks \
and should run on all Linux systems that need error handling."
HOMEPAGE = "http://mcelog.org/"
SECTION = "System Environment/Base"
DEPENDS += "bash"
SRC_URI = "git://git.kernel.org/pub/scm/utils/cpu/mce/mcelog.git"
SRCREV = "0fc9f702232cb2d9969916f899c67c3e64deedda"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://README;md5=3eb76ca64fa07ad53ebb0ebb5b4c8ede"

S = "${WORKDIR}/git"

do_install() {
    install -d ${D}/${sbindir}
    install -m 0755 ${S}/mcelog ${D}/${sbindir}
    install -d ${D}/${mandir}/man8
    install -m 0644 ${S}/mcelog.8 ${D}/${mandir}/man8/
    install -d ${D}/etc/cron.hourly
    install -m 0755 ${S}/mcelog.cron ${D}/etc/cron.hourly/
}

COMPATIBLE_HOST = '(x86_64.*|i.86.*)-linux'

RDEPENDS_${PN} = "bash"
