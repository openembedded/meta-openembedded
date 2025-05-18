SUMMARY = "AppStream is a collaborative effort for making machine-readable software metadata easily available."
HOMEPAGE = "https://github.com/ximion/appstream"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=435ed639f84d4585d93824e7da3d85da"

DEPENDS = " \
    appstream-native \
    curl-native \
    curl \
    docbook-xml-dtd4-native \
    gperf-native \
    glib-2.0 \
    libyaml \
    libxml2 \
    libxmlb \
    libxslt-native \
    itstool-native \
    docbook-xsl-stylesheets-native \
    python3-pygments-native \
"

inherit meson gobject-introspection gettext gtk-doc pkgconfig vala

GIR_MESON_OPTION = ""

SRC_URI = "https://www.freedesktop.org/software/appstream/releases/AppStream-${PV}.tar.xz"
SRC_URI:append:class-target = " file://0001-fix-crosscompile.patch"
SRC_URI[sha256sum] = "77e274e163db1f0a1bec8f4134b1d0f31e9c0a8c54cd37f724a7d30a71cf41d2"

S = "${WORKDIR}/AppStream-${PV}"

PACKAGECONFIG ?= "stemming ${@bb.utils.filter('DISTRO_FEATURES', 'systemd', d)}"

PACKAGECONFIG[systemd] = "-Dsystemd=true,-Dsystemd=false,systemd"
PACKAGECONFIG[stemming] = "-Dstemming=true,-Dstemming=false,libstemmer"

FILES:${PN} += "${datadir}"

EXTRA_OEMESON:class-target += "--cross-file=${WORKDIR}/meson-${PN}.cross"

do_write_config:append:class-target() {
    cat >${WORKDIR}/meson-${PN}.cross <<EOF
[binaries]
appstreamcli = '${STAGING_BINDIR_NATIVE}/appstreamcli'
EOF
}

BBCLASSEXTEND = "native"
