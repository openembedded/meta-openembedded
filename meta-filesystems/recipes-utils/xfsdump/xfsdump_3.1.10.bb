SUMMARY = "XFS Filesystem Dump Utility"
DESCRIPTION = "The xfsdump package contains xfsdump, xfsrestore and a \
               number of other utilities for administering XFS filesystems.\
               xfsdump examines files in a filesystem, determines which \
               need to be backed up, and copies those files to a \
               specified disk, tape or other storage medium."
HOMEPAGE = "http://oss.sgi.com/projects/xfs"
SECTION = "base"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://doc/COPYING;md5=15c832894d10ddd00dfcf57bee490ecc"
DEPENDS = "xfsprogs attr"

SRC_URI = "https://www.kernel.org/pub/linux/utils/fs/xfs/xfsdump/${BP}.tar.xz \
           file://remove-install-as-user.patch \
           ${@bb.utils.contains('DISTRO_FEATURES','usrmerge','file://0001-xfsdump-support-usrmerge.patch','',d)} \
           "
SRC_URI[sha256sum] = "9aab7a53aa05cd46edc97269ebf1456aab2b60ab8c1fffaaf8aa492f0b5f6517"

inherit autotools-brokensep

PARALLEL_MAKE = ""
PACKAGECONFIG ??= ""
PACKAGECONFIG[gettext] = "--enable-gettext=yes,--enable-gettext=no,gettext"

CFLAGS += "-D_FILE_OFFSET_BITS=64"

do_configure () {
    export DEBUG="-DNDEBUG"
    install -m 0755 ${STAGING_DATADIR_NATIVE}/gnu-config/config.guess ${S}
    install -m 0755 ${STAGING_DATADIR_NATIVE}/gnu-config/config.sub ${S}
    oe_runconf
}

do_install () {
    export DIST_ROOT=${D}
    oe_runmake install
    oe_runmake install-dev
}
