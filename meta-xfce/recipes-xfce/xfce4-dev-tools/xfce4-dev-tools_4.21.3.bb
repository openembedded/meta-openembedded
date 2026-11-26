SUMMARY = "Xfce4 development tools"
HOMEPAGE = "https://docs.xfce.org/xfce/xfce4-dev-tools/start"
SECTION = "x11/libs"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

DEPENDS = "glib-2.0 libxslt-native docbook-xsl-stylesheets-native"
DEPENDS:append:class-target = " ${BPN}-native"

SRC_URI = "https://archive.xfce.org/src/xfce/${BPN}/${@'${PV}'[0:4]}/${BP}.tar.xz \
           file://0001-m4macros-Check-for-a-function-provided-by-libX11-in-.patch \
           "
SRC_URI:append:class-target = " file://0001-Run-native-xdt-csource-on-tests.patch"
SRC_URI[sha256sum] = "e3137493ed47e36a4a177843e9043c7959eaf412e50600de4ab62d3454f50902"

UPSTREAM_CHECK_REGEX = "${BPN}-(?P<pver>\d+(\.\d+)+)\.tar"

inherit meson pkgconfig

do_install:append() {
    # drop the host tool paths found by meson from the release helper
    sed -i -e 's|${HOSTTOOLS_DIR}/||g' -e 's|${STAGING_BINDIR_NATIVE}/||g' ${D}${bindir}/xfce-do-release
}

FILES:${PN} += "${libdir}/cppcheck"

RDEPENDS:${PN} = "bash"

BBCLASSEXTEND = "native"
