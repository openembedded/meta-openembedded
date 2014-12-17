SUMMARY = "mcelog daemon accounts memory and some other errors in various ways."
DESCRIPTION = "mcelog is required by both 32bit x86 Linux kernels (since 2.6.30) \
and 64bit Linux kernels (since early 2.6 kernel releases) to log machine checks \
and should run on all Linux systems that need error handling."
HOMEPAGE = "http://mcelog.org/"
SECTION = "System Environment/Base"
DEPENDS += "bash"
SRC_URI = "git://git.kernel.org/pub/scm/utils/cpu/mce/mcelog.git"
SRC_URI[md5sum] = "a06761ad5fed8596238b5a5ea76ed662"
SRC_URI[sha256sum] = "519390825ec5a0545a7ed48717ef7c7480bb8b9bce68d63a58a52f4e4e275e0f"
SRCREV = "9de4924e751346df6e2d40ae1007b40ea1915e2b"
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
