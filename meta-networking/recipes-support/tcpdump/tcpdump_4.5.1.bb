SUMMARY = "A sophisticated network protocol analyzer"
HOMEPAGE = "http://www.tcpdump.org/"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1d4b0366557951c84a94fabe3529f867"
SECTION = "console/network"
DEPENDS = "libpcap"

SRC_URI = " \
    http://www.tcpdump.org/release/tcpdump-${PV}.tar.gz \
    file://tcpdump_configure_no_-O2.patch \
    file://0001-minimal-IEEE802.15.4-allowed.patch \
    file://ipv6-cross.patch \
    file://configure.patch \
    file://unnecessary-to-check-libpcap.patch \
    file://tcpdump-configure-dlpi.patch \
    file://tcpdump-cross-getaddrinfo.patch \
"
SRC_URI[md5sum] = "973a2513d0076e34aa9da7e15ed98e1b"
SRC_URI[sha256sum] = "14ab39657128f3a650aed4cf455f76c7590601087b6101c253d698f6e73f0b96"
export LIBS=" -lpcap"

inherit autotools-brokensep
CACHED_CONFIGUREVARS = "ac_cv_linux_vers=${ac_cv_linux_vers=2}"

EXTRA_OECONF = "--without-crypto \
        ${@base_contains('DISTRO_FEATURES', 'ipv6', '--enable-ipv6', '--disable-ipv6', d)}"

EXTRA_AUTORECONF += " -I m4"

do_configure_prepend() {
    mkdir -p ${S}/m4
    if [ -f aclocal.m4 ]; then
        mv aclocal.m4 ${S}/m4
    fi
    # AC_CHECK_LIB(dlpi.. was looking to host /lib
    sed -i 's:-L/lib::g' ./configure.in
}
do_configure_append() {
    sed -i 's:-L/usr/lib::' ./Makefile
    sed -i 's:-Wl,-rpath,${STAGING_LIBDIR}::' ./Makefile
    sed -i 's:-I/usr/include::' ./Makefile
}

do_install_append() {
    # tcpdump 4.0.0 installs a copy to /usr/sbin/tcpdump.4.0.0
    rm -f ${D}${sbindir}/tcpdump.${PV}
}
