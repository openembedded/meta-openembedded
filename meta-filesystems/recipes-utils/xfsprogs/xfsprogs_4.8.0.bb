SUMMARY = "XFS Filesystem Utilities"
HOMEPAGE = "http://oss.sgi.com/projects/xfs"
SECTION = "base"
LICENSE = "GPLv2 & LGPLv2.1"
LICENSE_libhandle = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://doc/COPYING;md5=dbdb5f4329b7e7145de650e9ecd4ac2a"
DEPENDS = "util-linux util-linux-native"
SRC_URI = "https://www.kernel.org/pub/linux/utils/fs/xfs/xfsprogs/${BP}.tar.xz \
           file://link_needed_libs.patch \
"
SRC_URI[md5sum] = "4f047bc9a28b48a95c6db0ad5ce4dbcb"
SRC_URI[sha256sum] = "82ce9cb3a55f4e208e8fe3471ff0aff0602b8300f3e50bdf05cc7e11549686f9"

inherit autotools-brokensep

PACKAGES =+ "${PN}-fsck ${PN}-mkfs ${PN}-repair libhandle"


RDEPENDS_${PN} = "${PN}-fsck ${PN}-mkfs ${PN}-repair"

FILES_${PN}-fsck = "${base_sbindir}/fsck.xfs"
FILES_${PN}-mkfs = "${base_sbindir}/mkfs.xfs"
FILES_${PN}-repair = "${base_sbindir}/xfs_repair"

FILES_libhandle = "${base_libdir}/libhandle${SOLIBS}"

EXTRA_OECONF = "--enable-gettext=no \
                INSTALL_USER=root \
                INSTALL_GROUP=root \
                ac_cv_header_aio_h=yes \
                ac_cv_lib_rt_lio_listio=yes \
"

EXTRA_AUTORECONF += "-I ${S}/m4 --exclude=autoheader"

PACKAGECONFIG ??= "readline blkid"

PACKAGECONFIG[readline] = "--enable-readline=yes,--enable-readline=no,readline"
PACKAGECONFIG[blkid] = "--enable-blkid=yes,--enable-blkid=no,util-linux"

export DEBUG="-DNDEBUG"

EXTRA_OEMAKE = "DIST_ROOT='${D}'"

do_configure_prepend () {
    export BUILD_CC="${BUILD_CC} ${BUILD_CFLAGS}"
    # Prevent Makefile from calling configure without arguments,
    # when do_configure gets called for a second time.
    rm -f ${B}/include/builddefs ${B}/include/platform_defs.h ${B}/configure
    # Recreate configure script.
    oe_runmake configure
}

do_install_append() {
        oe_runmake 'DESTDIR=${D}' install install-dev
}
