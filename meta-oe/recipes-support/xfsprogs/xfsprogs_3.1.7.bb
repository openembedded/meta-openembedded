DESCRIPTION = "XFS Filesystem Utilities"
HOMEPAGE = "http://oss.sgi.com/projects/xfs"
SECTION = "base"
LICENSE = "LGPL2.1"

LIC_FILES_CHKSUM = "file://Makefile;endline=3;md5=def2844770bb44eba37bc9ca8610fad4"

DEPENDS = "util-linux"

SRC_URI = "ftp://oss.sgi.com/projects/xfs/cmd_tars/${P}.tar.gz \
	file://remove-install-as-user.patch \
"
SRC_URI[md5sum] = "049cf9873794ea49d0bb3f12d45748a4"
SRC_URI[sha256sum] = "e150914210ac5fd29c098ef0fd94bdec51d2fb231cf9faa765c16ec6d75c8eaa"

inherit autotools

FILES_${PN}-dev += "${base_libdir}/libhandle.la \
                    ${base_libdir}/libhandle.so"

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
	# replace extra links to /usr/lib with relative links (otherwise autotools_prepackage_lamangler fails to read nonexistent link)
	rm -f ${D}/${base_libdir}/libhandle.la
	rm -f ${D}/${base_libdir}/libhandle.a
	ln -s ../usr/lib/libhandle.la ${D}/${base_libdir}/libhandle.la
	ln -s ../usr/lib/libhandle.a ${D}/${base_libdir}/libhandle.a

	# and link from /usr/lib/libhandle.so to /lib/libhandle.so
	rm -f ${D}/${libdir}/libhandle.so
	ln -s ../../lib/libhandle.a ${D}/${libdir}/libhandle.so
}
