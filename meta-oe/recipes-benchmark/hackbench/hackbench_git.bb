SUMMARY = "Linux kernel scheduler benchmark"
DESCRIPTION = "Hackbench is a benchmark for measuring Linux kernel scheduler performance. It creates groups of processes or threads that communicate via pipes or sockets and measures the time taken to complete the communication."
HOMEPAGE = "https://git.kernel.org/pub/scm/utils/rt-tests/rt-tests.git"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"

SRC_URI = "git://git.kernel.org/pub/scm/utils/rt-tests/rt-tests.git;protocol=https;branch=main"
SRCREV = "d232f528fd131b7d840e79a3756d1dbdaa9b1b60"

UPSTREAM_CHECK_COMMITS = "1"

DEPENDS = "numactl"

# Filter out unsupported compiler flags
CFLAGS:remove = "-fcanon-prefix-map"

do_configure[noexec] = "1"

do_compile() {
    oe_runmake NUMA=1 CC="${CC}" CFLAGS="${CFLAGS}" LDFLAGS="${LDFLAGS}" hackbench
}

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${B}/hackbench ${D}${bindir}/hackbench
}
