DESCRIPTION = "XFS Filesystem Utilities"
HOMEPAGE = "http://oss.sgi.com/projects/xfs"
SECTION = "base"
LICENSE = "GPLv2"
LICENSE_libhandle = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://doc/COPYING;md5=dbdb5f4329b7e7145de650e9ecd4ac2a"
DEPENDS = "util-linux"
PR = "r1"

SRC_URI = "ftp://oss.sgi.com/projects/xfs/cmd_tars/${P}.tar.gz \
	file://remove-install-as-user.patch \
"
SRC_URI[md5sum] = "049cf9873794ea49d0bb3f12d45748a4"
SRC_URI[sha256sum] = "e150914210ac5fd29c098ef0fd94bdec51d2fb231cf9faa765c16ec6d75c8eaa"

inherit autotools

PACKAGES =+ "${PN}-fsck ${PN}-mkfs libhandle"

RDEPENDS_${PN} = "${PN}-fsck ${PN}-mkfs"

FILES_${PN}-fsck = "${base_sbindir}/fsck.xfs"
FILES_${PN}-mkfs = "${base_sbindir}/mkfs.xfs"
FILES_libhandle = "${base_libdir}/libhandle${SOLIBS}"

EXTRA_OECONF = "--enable-gettext=no"
do_configure () {
	export DEBUG="-DNDEBUG"
	oe_runconf
}

LIBTOOL = "${HOST_SYS}-libtool"
EXTRA_OEMAKE = "'LIBTOOL=${LIBTOOL}'"
TARGET_CC_ARCH += "${LDFLAGS}"
PARALLEL_MAKE = ""

do_install () {
	export DIST_ROOT=${D}
	oe_runmake install
	# needed for xfsdump
	oe_runmake install-dev
	rm ${D}${base_libdir}/libhandle.a
	rm ${D}${base_libdir}/libhandle.la
	rm ${D}${base_libdir}/libhandle.so
	rm ${D}${libdir}/libhandle.so
	ln -s ../..${base_libdir}/libhandle.so.1 ${D}${libdir}/libhandle.so
}
