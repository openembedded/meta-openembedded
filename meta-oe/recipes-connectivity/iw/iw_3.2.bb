DESCRIPTION = "nl80211 based CLI configuration utility for wireless devices"
HOMEPAGE = "http://linuxwireless.org/en/users/Documentation/iw"
SECTION = "base"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=878618a5c4af25e9b93ef0be1a93f774"

PR = "r1"

DEPENDS = "libnl pkgconfig"

SRC_URI = "http://wireless.kernel.org/download/iw/${P}.tar.bz2 \
           file://0001-iw-version.sh-don-t-use-git-describe-for-versioning.patch \
          "

SRC_URI[md5sum] = "80615c972a287dcd50890735469b0d1c"
SRC_URI[sha256sum] = "370edabe6f73cc55be77a7502a3a25d23ec2aea900ae4ca3cec19d55bf8bdb12"

CFLAGS += "-DCONFIG_LIBNL30"

do_install() {
	oe_runmake DESTDIR=${D} install
}
