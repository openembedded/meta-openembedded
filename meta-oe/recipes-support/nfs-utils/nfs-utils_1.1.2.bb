DESCRIPTION = "userspace utilities for kernel nfs"
PRIORITY = "optional"
SECTION = "console/network"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=0636e73ff0215e8d672dc4c32c317bb3"

PR = "r9"

DEPENDS = "util-linux tcp-wrappers libevent"

SRC_URI = "${SOURCEFORGE_MIRROR}/nfs/nfs-utils-${PV}.tar.gz \
	file://nfs-utils-tools-unset-cflags.patch \
	file://nfs-utils-uclibc-compile.patch \
	file://nfs-utils-S_ISREG-missing-include.patch \
	file://nfsserver \
   "

S = "${WORKDIR}/nfs-utils-${PV}/"

PARALLEL_MAKE = ""

# Only kernel-module-nfsd is required here (but can be built-in)  - the nfsd module will
# pull in the remainder of the dependencies.
RDEPENDS_${PN} = "portmap"
RRECOMMENDS_${PN} = "kernel-module-nfsd"

INITSCRIPT_NAME = "nfsserver"
# The server has no dependencies at the user run levels, so just put
# it in at the default levels.  It must be terminated before the network
# in the shutdown levels, but that works fine.
INITSCRIPT_PARAMS = "defaults"

inherit autotools update-rc.d

EXTRA_OECONF = "--with-statduser=nobody \
		--enable-nfsv3 \
		--disable-nfsv4 \
		--disable-gss \
		--with-statedir=/var/lib/nfs"

INHIBIT_AUTO_STAGE = "1"

do_install_append() {
	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/nfsserver ${D}${sysconfdir}/init.d/nfsserver

	rm ${D}${sbindir}/rpcdebug
}

PACKAGES =+ "nfs-utils-client"
FILES_nfs-utils-client = "${base_sbindir}/*mount.nfs*"

SRC_URI[md5sum] = "76ee9274c2b867839427eba91b327f03"
SRC_URI[sha256sum] = "1d09b1c133e4eed9d2df07ead4eba813e6993875c39e66d7b4081287029d4033"
