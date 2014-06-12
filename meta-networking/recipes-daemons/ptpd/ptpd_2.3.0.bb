SUMMARY = "The PTP daemon (PTPd)"
DESCRIPTION = "The PTP daemon (PTPd) implements the Precision Time protocol (PTP) as \
defined by the relevant IEEE 1588 standard. PTP Version 1 implements IEEE-1588-2002, \
and PTP Version 2 implements IEEE-1588-2008. PTP was developed to provide very precise \
time coordination of LAN connected computers."
HOMEPAGE = "http://sourceforge.net/projects/ptpd"
SECTION = "network"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=4019cad2b3cd55f22ea819ffad6ccd41"

DEPENDS = "libpcap"

inherit autotools

SRC_URI = "http://downloads.sourceforge.net/project/ptpd/ptpd/${PV}/ptpd-${PV}.tar.gz"

S = "${WORKDIR}/ptpd-${PV}"
SRC_URI[md5sum] = "f5e931b4a229705ff0dbdfe22490566b"
SRC_URI[sha256sum] = "1a4e90496f004bfd91657ccc49209101dc25b787e540648c07c0973469f1d8f7"

EXTRA_OEMAKE = ""

EXTRA_OECONF += "--disable-snmp"

do_install() {
    install -d ${D}${bindir} ${D}${mandir}/man8
    install -m 0755 ${B}/src/ptpd2 ${D}${bindir}
    install -m 0644 ${B}/src/ptpd2.8 ${D}${mandir}/man8
}
