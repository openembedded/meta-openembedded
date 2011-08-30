DESCRIPTION = "Tests large file IO and creation/deletion of small files."
HOMEPAGE = "http://www.coker.com.au/bonnie++/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://copyright.txt;md5=cd4dde95a6b9d122f0a9150ae9cc3ee0"

SRC_URI = "http://www.coker.com.au/bonnie++/bonnie++-${PV}.tgz \
           file://gcc-4.3-fixes.patch \
           "
SRC_URI[md5sum] = "77a1ba78f37bdd7f024b67e1e36ad151"
SRC_URI[sha256sum] = "c674f1182f4c20f1e6d038feceb0a6617fc3e7658dfbbac89396043b49612a26"

inherit autotools

SCRIPTS = "bon_csv2html bon_csv2txt"
EXES = "bonnie++ zcav"

TARGET_CC_ARCH += "${LDFLAGS}"

do_install () {
        install -d ${D}/bin
        install -d ${D}/sbin
        install -m 0755 ${EXES} ${D}/sbin
        install -m 0755 ${SCRIPTS} ${D}/bin
}

PACKAGES =+ "bonnie-scripts"

FILES_${PN} = "${base_sbindir}"
FILES_bonnie-scripts = "${base_bindir}"



