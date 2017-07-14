SUMMARY = "XFS Filesystem Dump Utility"
DESCRIPTION = "The xfsdump package contains xfsdump, xfsrestore and a \
               number of other utilities for administering XFS filesystems.\
               xfsdump examines files in a filesystem, determines which \
               need to be backed up, and copies those files to a \
               specified disk, tape or other storage medium."
HOMEPAGE = "http://oss.sgi.com/projects/xfs"
SECTION = "base"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://doc/COPYING;md5=15c832894d10ddd00dfcf57bee490ecc"
DEPENDS = "xfsprogs attr"

SRC_URI = "ftp://oss.sgi.com/projects/xfs/cmd_tars/${BPN}-${PV}.tar.gz \
           file://remove-install-as-user.patch \
           file://0001-Include-fcntl.h-for-O_EXCL.patch \
           file://0002-Replace-__uint32_t-with-uint32_t.patch \
           file://0003-replace-use-of-SIGCLD-with-SIGCHLD.patch \
           file://0004-include-limit.h-for-PATH_MAX.patch \
           file://0005-include-sys-types.h-for-u_int32_t-in-attr-attributes.patch \
           "
SRC_URI[md5sum] = "50353cd4f4b435685955363e6044f4d1"
SRC_URI[sha256sum] = "7f78c11ca527477d90e5e62b0778f3ad96f2b71c19173044e9aca9515fff42d0"

inherit autotools-brokensep

PARALLEL_MAKE = ""
PACKAGECONFIG ??= ""
PACKAGECONFIG[gettext] = "--enable-gettext=yes,--enable-gettext=no,gettext"

CFLAGS += "-D_FILE_OFFSET_BITS=64"

EXTRA_OEMAKE += "'LIBTOOL=${HOST_SYS}-libtool' V=1"

do_configure () {
    export DEBUG="-DNDEBUG"
    oe_runconf
}

do_install () {
    export DIST_ROOT=${D}
    oe_runmake install
    oe_runmake install-dev
}
