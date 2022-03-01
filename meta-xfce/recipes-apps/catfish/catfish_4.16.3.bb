SUMMARY = "Catfish is a handy file searching tool for linux and unix"
SECTION = "x11/application"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=4325afd396febcb659c36b49533135d4"

inherit xfce-app setuptools_build_meta gtk-icon-cache mime-xdg
PYPA_WHEEL = "${B}/dist/UNKNOWN-*-*.whl"

DEPENDS += "python3-distutils-extra-native"

SRC_URI[sha256sum] = "e9a99a62d10981391508dd43f3cbfa2d50a69bd6b7d1eeef7d30ba4c673dcfda"

FILES:${PN} += "${datadir}/metainfo"

RDEPENDS:${PN} += "python3-pygobject python3-dbus"
