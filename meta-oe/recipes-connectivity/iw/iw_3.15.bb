SUMMARY = "nl80211 based CLI configuration utility for wireless devices"
DESCRIPTION = "iw is a new nl80211 based CLI configuration utility for \
wireless devices. It supports almost all new drivers that have been added \
to the kernel recently. "
HOMEPAGE = "http://wireless.kernel.org/en/users/Documentation/iw"
SECTION = "base"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=878618a5c4af25e9b93ef0be1a93f774"

DEPENDS = "libnl pkgconfig"

SRC_URI = "http://www.kernel.org/pub/software/network/iw/${BP}.tar.gz \
           file://0001-iw-version.sh-don-t-use-git-describe-for-versioning.patch \
"

SRC_URI[md5sum] = "ebb16e6c29b075e3a58b99552583fd79"
SRC_URI[sha256sum] = "1223ebab68dc337f16ed80c45af37b78f112ea091e919eafe96a4cbd63942081"

EXTRA_OEMAKE = ""

do_install() {
    oe_runmake DESTDIR=${D} install
}
