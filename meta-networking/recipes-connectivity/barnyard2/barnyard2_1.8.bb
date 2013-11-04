DESCRIPTION = "Barnyard2 is a fork of the original barnyard project, designed specifically for Snort's new unified2 file format"
HOMEPAGE = "http://www.securixlive.com/barnyard2/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=751419260aa954499f7abaabaa882bbe"
DEPENDS = "libpcap"

SRC_URI = " \
	http://www.securixlive.com/download/barnyard2/${BP}.tar.gz \
	file://barnyard2-cross-fix.patch \
	"
SRC_URI[md5sum] = "72fc6c490db6ea6a0f46c27d24998067"
SRC_URI[sha256sum] = "1abfe6530d721debd98921421722b02c844c289564730b1e85729e4915b2bb7a"

inherit autotools pkgconfig

EXTRA_OECONF = "--enable-prelude --enable-gre --without-mysql"

CONFFILES_${PN} = "${sysconfdir}/barnyard2.conf"
