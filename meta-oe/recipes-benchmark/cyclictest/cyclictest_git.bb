SUMMARY = "Real-time latency measurement tool"
DESCRIPTION = "Cyclictest is a tool for measuring real-time latency and jitter on Linux systems. It measures the latency of the kernel scheduler."
HOMEPAGE = "https://git.kernel.org/pub/scm/utils/rt-tests/rt-tests.git"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"

SRC_URI = "git://git.kernel.org/pub/scm/utils/rt-tests/rt-tests.git;protocol=https;branch=main"
SRCREV = "7aca81171ea05a03d0e8698af4ae53c5177f5f83"

UPSTREAM_CHECK_COMMITS = "1"

DEPENDS = "numactl"

# Filter out unsupported compiler flags
CFLAGS:remove = "-fcanon-prefix-map"

do_configure[noexec] = "1"

do_compile() {
    oe_runmake NUMA=1 CC="${CC}" CFLAGS="${CFLAGS}" LDFLAGS="${LDFLAGS}" cyclictest
}

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${B}/cyclictest ${D}${bindir}/cyclictest
}
