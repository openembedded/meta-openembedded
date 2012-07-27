DESCRIPTION = "With FUSE it is possible to implement a fully functional filesystem in a userspace program"
HOMEPAGE = "http://fuse.sf.net"
SECTION = "libs"
LICENSE = "GPLv2 & LGPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
                    file://COPYING.LIB;md5=4fbd65380cdd255951079008b364516c"

SRC_URI = "${SOURCEFORGE_MIRROR}/fuse/fuse-${PV}.tar.gz \
           file://gold-unversioned-symbol.patch \
          "
SRC_URI[md5sum] = "c646133c7bbf8ad9d2ced0888dc4a319"
SRC_URI[sha256sum] = "51803d8224bf6adab052b340614980b28861f317c261eab1f1e9c6cf17b3dd75"

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
