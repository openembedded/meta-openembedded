DESCRIPTION = "With FUSE it is possible to implement a fully functional filesystem in a userspace program"
HOMEPAGE = "http://fuse.sf.net"
SECTION = "libs"
LICENSE = "GPLv2&LGPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f \
                    file://COPYING.LIB;md5=f30a9716ef3762e3467a2f62bf790f0a"

PR = "r1"

SRC_URI = "${SOURCEFORGE_MIRROR}/fuse/fuse-${PV}.tar.gz \
           file://define_GNU_SOURCE.patch \
           file://gold-unversioned-symbol.patch \
          "

SRC_URI[md5sum] = "eaa32c8cef56a981656a786f258a002a"
SRC_URI[sha256sum] = "1ec1913e38f09b2a9ec1579e1800805b5e2c747d1dce515e316dbb665ca139d6"

inherit autotools pkgconfig

EXTRA_OECONF = "--disable-kernel-module"

PACKAGES =+ "fuse-utils-dbg fuse-utils libulockmgr libulockmgr-dev libulockmgr-dbg"

RRECOMMENDS_${PN} = "kernel-module-fuse""

FILES_${PN} += "${libdir}/libfuse.so.*"
FILES_${PN}-dev += "${libdir}/libfuse*.la"

FILES_libulockmgr = "${libdir}/libulockmgr.so.*"
FILES_libulockmgr-dev += "${libdir}/libulock*.la"
FILES_libulockmgr-dbg += "${libdir}/.debug/libulock*"

# Forbid auto-renaming to libfuse-utils
FILES_fuse-utils = "${bindir} ${base_sbindir}"
FILES_fuse-utils-dbg = "${bindir}/.debug ${base_sbindir}/.debug"
DEBIAN_NOAUTONAME_fuse-utils = "1"
DEBIAN_NOAUTONAME_fuse-utils-dbg = "1"
