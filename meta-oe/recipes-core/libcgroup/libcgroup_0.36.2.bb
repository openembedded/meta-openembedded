DESCRIPTION = "Libcgroup"
PRIORITY = "optional"
SECTION = "libs"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=2d5025d4aa3495befef8f17206a5b0a1"

inherit autotools pkgconfig

DEPENDS = "${@base_contains('DISTRO_FEATURES', 'pam', 'libpam', '', d)}"

SRC_URI = "${SOURCEFORGE_MIRROR}/project/libcg/${PN}/v${PV}/${PN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "2b0fedf37e8f3e915a2f4f3f10879076"
SRC_URI[sha256sum] = "7fdb171c09d7e9d13d118045a5651b8c146ede979c05aca0f6bb20624e6a73e3"

PACKAGES =+ "cgroups-pam-plugin"
FILES_cgroups-pam-plugin = "${libdir}/security/pam_cgroup.so*"

