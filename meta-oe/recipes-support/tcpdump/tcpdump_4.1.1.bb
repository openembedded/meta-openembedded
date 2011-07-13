DESCRIPTION = "A sophisticated network protocol analyzer"
HOMEPAGE = "http://www.tcpdump.org/"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1d4b0366557951c84a94fabe3529f867"
SECTION = "console/network"
DEPENDS = "libpcap"
PR = "r1"

SRC_URI = " \
	http://www.tcpdump.org/release/tcpdump-${PV}.tar.gz \
	file://tcpdump_configure_no_-O2.patch \
	file://0001-minimal-IEEE802.15.4-allowed.patch \
	file://ipv6-cross.patch \
	file://configure.patch \
"

inherit autotools
# ac_cv_linux_vers=${ac_cv_linux_vers=2}

EXTRA_OECONF = "--without-crypto \
		${@base_contains('DISTRO_FEATURES', 'ipv6', '--enable-ipv6', '--disable-ipv6', d)}"

do_configure() {
	# AC_CHECK_LIB(dlpi.. was looking to host /lib
	sed -i 's:-L/lib:-L${STAGING_LIBDIR}:g' ./configure.in
        
	gnu-configize
	autoconf
	oe_runconf
	sed -i 's:/usr/lib:${STAGING_LIBDIR}:' ./Makefile
	sed -i 's:/usr/include:${STAGING_INCDIR}:' ./Makefile
}

do_install_append() {
	# tcpdump 4.0.0 installs a copy to /usr/sbin/tcpdump.4.0.0
	rm -f ${D}${sbindir}/tcpdump.${PV}
}

SRC_URI[md5sum] = "d0dd58bbd6cd36795e05c6f1f74420b0"
SRC_URI[sha256sum] = "e6cd4bbd61ec7adbb61ba8352c4b4734f67b8caaa845d88cb826bc0b9f1e7f0a"

