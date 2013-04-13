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
    file://drop-configure-check-for-aio.patch \
"

SRC_URI[md5sum] = "f70b2e7200d4c29f0af1cf70e7be1db6"
SRC_URI[sha256sum] = "74409e2e3748074999df25c00f722621659a0bd3607e677f0bcc4373b8c93eab"

inherit autotools

PACKAGES =+ "${PN}-fsck ${PN}-mkfs libhandle"

RDEPENDS_${PN} = "${PN}-fsck ${PN}-mkfs"

FILES_${PN}-fsck = "${base_sbindir}/fsck.xfs"
FILES_${PN}-mkfs = "${base_sbindir}/mkfs.xfs"
FILES_libhandle = "${base_libdir}/libhandle${SOLIBS}"

EXTRA_OECONF = "--enable-gettext=no"
do_configure () {
    # Prevent Makefile from calling configure without arguments,
    # when do_configure gets called for a second time.
    rm -f include/builddefs include/platform_defs.h
    # Recreate configure script.
    rm -f configure
    oe_runmake configure
    # Configure.
    export DEBUG="-DNDEBUG"
    gnu-configize --force
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
