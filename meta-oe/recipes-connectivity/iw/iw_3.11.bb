SUMMARY = "nl80211 based CLI configuration utility for wireless devices"
DESCRIPTION = "iw is a new nl80211 based CLI configuration utility for \
wireless devices. It supports almost all new drivers that have been added \
to the kernel recently. "
HOMEPAGE = "http://wireless.kernel.org/en/users/Documentation/iw"
SECTION = "base"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=878618a5c4af25e9b93ef0be1a93f774"

DEPENDS = "libnl pkgconfig"

SRC_URI = "http://www.kernel.org/pub/software/network/iw/${BP}.tar.bz2 \
           file://0001-iw-version.sh-don-t-use-git-describe-for-versioning.patch \
"

SRC_URI[md5sum] = "e633cf7c875c7d8b547abafc0d95f6c4"
SRC_URI[sha256sum] = "09348d4f7371fad00c07cfb67b9e34f24403cbd9361f9634cfb4dff9cdd40139"

EXTRA_OEMAKE = ""

do_install() {
    oe_runmake DESTDIR=${D} install
}
