SUMMARY = "Xfce4 development tools"
HOMEPAGE = "http://www.xfce.org"
SECTION = "x11/libs"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=9ac2e7cff1ddaf48b6eab6028f23ef88"
DEPENDS = "glib-2.0"

inherit autotools pkgconfig

BBCLASSEXTEND = "native"

SRC_URI = " \
    http://archive.xfce.org/src/xfce/${BPN}/${@'${PV}'[0:4]}/${BPN}-${PV}.tar.bz2 \
    file://xdt-autogen_dependency.patch \
"
SRC_URI[md5sum] = "36112d0256092c30bd1b47105c547edf"
SRC_URI[sha256sum] = "2dccdd4935716a97db28464ba2403572ce03134fd7adf294e1a59eaf297e6555"

do_install_append() {
    install -d ${D}${datadir}/aclocal
    install -m 644 ${S}/m4macros/*.m4 ${D}${datadir}/aclocal/
}

FILES_${PN} += "${datadir}/xfce4/dev-tools/m4macros/*.m4"

RDEPENDS_${PN} = "bash"
