SUMMARY = "System-level performance monitoring and performance management"
HOMEPAGE = "http://www.pcp.io"
SECTION =  "Applications/System"

LICENSE = "GPL-2.0-or-later & LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=37ab75b580d5aad4ada04260efa3702f \
                    "
COMPATIBLE_HOST:libc-musl = "null"

SRC_URI = "git://git@github.com/performancecopilot/pcp.git;protocol=https;nobranch=1 \
           file://0001-configure-Limit-the-header-search-to-sysroot.patch \
           file://config.linux \
	   "
SRCREV = "520c142c23f87dc01c4e419d4b56e80069546dc2"

S = "${WORKDIR}/git"

inherit setuptools3-base pkgconfig autotools-brokensep

# Specify any options you want to pass to the configure script using EXTRA_OECONF:
CACHED_CONFIGUREVARS = "PACKAGE_DISTRIBUTION=arch"
EXTRA_OECONF:append = " --with-dstat-symlink=yes --with-infiniband=no \
                --with-pmdapodman=no --with-python=no --with-python3=yes --with-perl=no \
		--with-pmdabcc=no --with-pmdajson=no  --with-perfevent=no \
		--with-pmdabcc=yes \
		--with-rundir=${localstatedir}/run \
                --with-sysconfigdir=${sysconfdir} \
                --with-logdir=${localstatedir}/log \
                --with-docdir=${docdir} \
                "

PACKAGECONFIG[qt5] = "--with-qt=yes --with-qt3d=yes,--with-qt=no --with-qt3d=no,qtbase qt3d"

PACKAGECONFIG ?= ""
