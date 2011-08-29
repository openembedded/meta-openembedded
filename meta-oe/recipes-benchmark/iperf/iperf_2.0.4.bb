DESCRIPTION = "Iperf is a tool to measure maximum TCP bandwidth, allowing the tuning of various parameters and UDP characteristics"
HOMEPAGE = "http://dast.nlanr.net/Projects/Iperf/"
SECTION = "console/network"
LICENSE = "NewBSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=e8478eae9f479e39bc34975193360298"

SRC_URI = " \
	${SOURCEFORGE_MIRROR}/iperf/iperf-${PV}.tar.gz \
        file://000-Iperf_Fix-CPU-Usage.diff \
        file://001-cast-to-max_size_t-instead-of-int.patch \
        file://003-fix-hyphen-used-as-minus-sign.patch \
        file://004-svn-r43-ro.patch \
        file://005-iperf-die-on-bind-fail.patch \
        file://006-iperf-die-on-connect-fail.patch \
        file://007-iperf-reporter-deadlock.patch \
        file://008-numofreport.patch \
        file://009-delayloop.patch \
	"


SRC_URI[md5sum] = "8c5bc14cc2ea55f18f22afe3c23e3dcb"
SRC_URI[sha256sum] = "3b52f1c178d6a99c27114929d5469c009197d15379c967b329bafb956f397944"

inherit autotools

S="${WORKDIR}/iperf-${PV}"

EXTRA_OECONF = "--exec-prefix=${STAGING_DIR_HOST}${layout_exec_prefix}"

do_configure() {
	export ac_cv_func_malloc_0_nonnull=yes
	gnu-configize
	oe_runconf
}

do_compile() {
	cd ${WORKDIR}/iperf-${PV}
	oe_runmake
}

do_install() {
	cd ${WORKDIR}/iperf-${PV}/src
	oe_runmake DESTDIR=${D} install
}


