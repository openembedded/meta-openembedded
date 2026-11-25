DESCRIPTION = "Avahi Module for Apache2."
HOMEPAGE = "https://0pointer.de/lennart/projects/mod_dnssd/"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

DEPENDS = "apache2 avahi"

SRC_URI = "https://0pointer.de/lennart/projects/mod_dnssd/mod_dnssd-${PV}.tar.gz \
           file://0001-build-system-properly-quote-CFLAGS-LIBS-for-apxs.patch \
           file://0002-mod_dnssd.c-Use-ap_unixd_setup_child.patch \
           "
SRC_URI[sha256sum] = "2cd171d76eba398f03c1d5bcc468a1756f4801cd8ed5bd065086e4374997c5aa"

UPSTREAM_CHECK_REGEX = "mod_dnssd-(?P<pver>\d+(\.\d+)+)\.tar"

S = "${UNPACKDIR}/mod_dnssd-${PV}"

EXTRA_OECONF = "--disable-lynx"

inherit autotools pkgconfig

do_install() {
	install -Dm755 ${S}/src/.libs/mod_dnssd.so ${D}${libexecdir}/apache2/modules/mod_dnssd.so
}

