SUMMARY = "Portable Hardware Locality (hwloc) software package"
DESCRIPTION = "The Portable Hardware Locality (hwloc) software package \
 provides a portable abstraction of the hierarchical topology of modern \
 architectures."
HOMEPAGE = "https://www.open-mpi.org/software/hwloc/"
SECTION = "base"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=3282e20dc3cec311deda3c6d4b1f990b"

SRC_URI = "https://www.open-mpi.org/software/${PN}/v1.11/downloads/${BP}.tar.bz2"
SRC_URI[md5sum] = "96c34136ff416d2b13a7821c27477bed"
SRC_URI[sha256sum] = "95d80286dfe658a3f79e2ac90698782bb36e5504f4bac1bba2394ba14dbbad24"

inherit autotools

DEPENDS += "cairo ncurses udev libxml2 zlib libpciaccess"
DEPENDS += "${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'virtual/libx11', '', d)}"
DEPENDS += "${@bb.utils.contains('DISTRO_FEATURES', 'selinux', 'libselinux', '', d)}"

# Split hwloc library into separate subpackage
PACKAGES_prepend = " lib${PN} "
FILES_lib${PN} += "${libdir}/lib${PN}.so*"
RDEPENDS_${PN} += "lib${PN} (= ${EXTENDPKGV})"

# XXX dev-so QA check doesn't like soname symlinks in non-dev packages
INSANE_SKIP_lib${PN} += "dev-so"
