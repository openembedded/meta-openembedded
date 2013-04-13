SUMMARY = "The PTP daemon (PTPd)"
DESCRIPTION = "The PTP daemon (PTPd) implements the Precision Time protocol (PTP) as \
defined by the relevant IEEE 1588 standard. PTP Version 1 implements IEEE-1588-2002, \
and PTP Version 2 implements IEEE-1588-2008. PTP was developed to provide very precise \
time coordination of LAN connected computers."
HOMEPAGE = "http://sourceforge.net/projects/ptpd"
SECTION = "network"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://../COPYRIGHT;md5=3d8ac2c46c116bce2d2ad838b6cf3491"

PR = "r1"

SRC_URI = "http://downloads.sourceforge.net/project/ptpd/ptpd/${PV}/ptpd-${PV}.tar.gz \
           file://ld-as-needed.patch;striplevel=2 \
"

SRC_URI[md5sum] = "c63a3a149d30c710773ccb02df5782a3"
SRC_URI[sha256sum] = "f2266a22db84318d8b9ce266ea83772c03438c31f4993fa9643fa675a07c26b4"

S = "${WORKDIR}/ptpd-${PV}/src"

EXTRA_OEMAKE = ""

do_install() {
    install -d ${D}${bindir} ${D}${mandir}/man8
    install -m 0755 ptpd2 ${D}${bindir}
    install -m 0644 ptpd2.8 ${D}${mandir}/man8
}
