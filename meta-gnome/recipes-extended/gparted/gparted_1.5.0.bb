SUMMARY = "A partition editor to graphically manage disk partitions "
HOMEPAGE = "http://gparted.org/index.php"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

inherit autotools pkgconfig python3native gettext gnome-help gtk-icon-cache features_check

ANY_OF_DISTRO_FEATURES = "${GTK3DISTROFEATURES}"

SRC_URI = " \
    ${SOURCEFORGE_MIRROR}/project/${BPN}/${BPN}/${BPN}-${PV}/${BPN}-${PV}.tar.gz \
    ${@bb.utils.contains('DISTRO_FEATURES', 'polkit', 'file://0001-Install-polkit-action-unconditionally-executable-pke.patch', '', d)} \
"
SRC_URI[sha256sum] = "3c95ea26a944083ff1d9b17639b1e2ad9758df225dc751ff407b2a6aa092a8de"

UPSTREAM_CHECK_URI = "https://sourceforge.net/projects/gparted/files/gparted/"
UPSTREAM_CHECK_REGEX = "gparted-(?P<pver>\d+\.(\d+)+(\.\d+)+)"

DEPENDS += " \
    glib-2.0-native \
    yelp-tools-native \
    intltool-native \
    glib-2.0 \
    gtkmm3 \
    parted \
"

PACKAGECONFIG = "${@bb.utils.filter('DISTRO_FEATURES', 'polkit', d)}"
PACKAGECONFIG[polkit] = ",,polkit"

FILES:${PN} += " \
    ${datadir}/appdata \
    ${datadir}/icons \
"

PACKAGES += "${PN}-polkit"
FILES:${PN}-polkit = "${datadir}/polkit-1"

RDEPENDS:${PN} = " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'polkit', '${PN}-polkit', '', d)} \
    dosfstools \
    mtools \
    e2fsprogs \
"
