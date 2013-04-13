SUMMARY = "nl80211 based CLI configuration utility for wireless devices"
DESCRIPTION = "iw is a new nl80211 based CLI configuration utility for \
wireless devices. It supports almost all new drivers that have been added \
to the kernel recently. "
HOMEPAGE = "http://linuxwireless.org/en/users/Documentation/iw"
SECTION = "base"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=878618a5c4af25e9b93ef0be1a93f774"

DEPENDS = "libnl pkgconfig"

SRC_URI = "http://wireless.kernel.org/download/iw/${P}.tar.bz2 \
           file://0001-iw-version.sh-don-t-use-git-describe-for-versioning.patch \
"

SRC_URI[md5sum] = "1c18bfbbc8773629e5e8ac733a39540c"
SRC_URI[sha256sum] = "df11036ac11df31f44083da962a6e9c74bdea7c01c596c4b5840f948cdb6c857"

EXTRA_OEMAKE = ""

do_install() {
    oe_runmake DESTDIR=${D} install
}
