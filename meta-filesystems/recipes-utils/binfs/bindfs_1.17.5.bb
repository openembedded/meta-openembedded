SUMMARY = "A FUSE filesystem for mounting a directory to another location"
DESCRIPTION = "bindfs is a FUSE filesystem for mounting a directory to another location, similarly to mount --bind."
HOMEPAGE = "https://bindfs.org/"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

DEPENDS = "fuse"
RDEPENDS:${PN} = "fuse"

SRC_URI = "git://github.com/mpartel/bindfs;protocol=https;branch=master"
SRCREV = "3f57fa69448ad68f31cf62cf75b5a9b2e5096c05"

S = "${WORKDIR}/git"

inherit autotools pkgconfig gettext
