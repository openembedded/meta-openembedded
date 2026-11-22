SUMMARY = "X11 Mouse Theme"
HOMEPAGE = "https://github.com/ducakar/openzone-cursors"
SECTION = "x11/wm"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=15c85787ab411af36a78dfc20fdf7793"

DEPENDS = "icon-slicer-native"

inherit allarch gtk-icon-cache

SRC_URI = "git://github.com/ducakar/openzone-cursors.git;protocol=https;branch=master;tag=v${PV}"
SRCREV = "18d11a476536fbf4d69bb981c4af934a44c95fc4"

UPSTREAM_CHECK_GITTAGREGEX = "v(?P<pver>\d+(\.\d+)+)"

# The Makefile's install target moves the themes and keeps the build user's ownership
do_install() {
    install -d ${D}${datadir}/icons
    cp -R --no-preserve=ownership ${B}/OpenZone_* ${D}${datadir}/icons/
}

python populate_packages:prepend () {
    icondir = d.expand('${datadir}/icons/')
    iconname = d.expand('${PN}-%s')
    do_split_packages(d, icondir, '^(.*)',
                      iconname,
                      'Open Zone cursors %s',
                      extra_depends='',
                      prepend=True,
                      aux_files_pattern=['${datadir}/icons/%s/*'])
}

FILES:${PN} += "${datadir}/icons"

PACKAGES_DYNAMIC += "^${PN}-.*"
ALLOW_EMPTY:${PN} = "1"
