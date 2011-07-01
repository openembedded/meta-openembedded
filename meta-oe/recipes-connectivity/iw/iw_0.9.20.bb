DESCRIPTION = "nl80211 based CLI configuration utility for wireless devices"
HOMEPAGE = "http://linuxwireless.org/en/users/Documentation/iw"
SECTION = "base"
PRIORITY = "optional"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=878618a5c4af25e9b93ef0be1a93f774"

DEPENDS = "libnl pkgconfig"

SRC_URI = " \
	http://wireless.kernel.org/download/iw/${P}.tar.bz2 \
	file://kill-git-version-check.patch \
	"

SRC_URI[md5sum] = "1055799b544b3e08169d155bb6c21bb0"
SRC_URI[sha256sum] = "2d665fbfbe4fd6a40159fb821518fc5337da37448416405b936a393a14cbc7d3"

CFLAGS += "-DCONFIG_LIBNL20"

do_compile() {
	oe_runmake
}

do_install() {
	oe_runmake DESTDIR=${D} install
}
