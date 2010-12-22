DESCRIPTION = "Gives a fake root environment"
HOMEPAGE = "http://fakeroot.alioth.debian.org"
SECTION = "base"
LICENSE = "GPL"
PR = "r2"

SRC_URI = "\
  ${DEBIAN_MIRROR}/main/f/fakeroot/fakeroot_${PV}.tar.gz \
  file://configure-libtool.patch \
"
	    
inherit autotools

do_install_append() {
        install -d ${D}${includedir}/fakeroot
        install -m 644 *.h ${D}${includedir}/fakeroot
        install -d ${D}${libdir}/libfakeroot/
        oe_libinstall -so libfakeroot ${D}${libdir}/libfakeroot/
}

# fakeroot needs getopt which is provided by the util-linux package
RDEPENDS_${PN} = "util-linux"


SRC_URI[md5sum] = "aaefede2405a40c87438e7e833d69b70"
SRC_URI[sha256sum] = "dbcab1f495b857e67feff882e018ca59958b8d189ff1f76684d28e35463ec29d"
