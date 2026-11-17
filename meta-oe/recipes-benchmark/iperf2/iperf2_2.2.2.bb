DESCRIPTION = "Iperf is a tool to measure maximum TCP bandwidth, allowing the tuning of various parameters and UDP characteristics"
HOMEPAGE = "https://sourceforge.net/projects/iperf2/"
SECTION = "console/network"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://COPYING;md5=e136a7b2560d80bcbf0d9b3e1356ecff"

SRC_URI = "${SOURCEFORGE_MIRROR}/${BPN}/iperf-${PV}.tar.gz"

SRC_URI[sha256sum] = "4008e1dc7cdeb68605747e11e954a5b25f4646ab6d71207d979acec5de7d385a"

UPSTREAM_CHECK_URI = "https://sourceforge.net/projects/${BPN}/files/"
UPSTREAM_CHECK_REGEX = "iperf-(?P<pver>\d+(\.\d+)+)\.tar\.gz"


S = "${UNPACKDIR}/iperf-${PV}"

inherit autotools pkgconfig

EXTRA_OECONF = "--exec-prefix=${STAGING_DIR_HOST}${layout_exec_prefix}"

PACKAGECONFIG ??= "${@bb.utils.contains('DISTRO_FEATURES', 'ipv6', 'ipv6', '', d)}"
PACKAGECONFIG[ipv6] = "--enable-ipv6,--disable-ipv6,"

CVE_PRODUCT:append = " iperf_project:iperf"
CVE_STATUS[CVE-2025-54349] = "cpe-incorrect: the vulnerability is in iperf3, which is a different project"
CVE_STATUS[CVE-2025-54350] = "cpe-incorrect: the vulnerability is in iperf3, which is a different project"
CVE_STATUS[CVE-2025-54351] = "cpe-incorrect: the vulnerability is in iperf3, which is a different project"
