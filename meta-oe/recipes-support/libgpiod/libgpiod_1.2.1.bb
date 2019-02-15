require libgpiod.inc

DEPENDS += "autoconf-archive-native"

SRC_URI[md5sum] = "e6c222512a0d1994a069ebfd2e0a56fd"
SRC_URI[sha256sum] = "736d8b511ad247c2acb01b592f2bbe5e757e14e1d8347b2d80683081ab4b31b8"

PACKAGECONFIG[cxx] = "--enable-bindings-cxx,--disable-bindings-cxx"

PACKAGECONFIG[python3] = "--enable-bindings-python,--disable-bindings-python,python3"
inherit ${@bb.utils.contains('PACKAGECONFIG', 'python3', 'python3native', '', d)}

PACKAGES =+ "${PN}-python"
FILES_${PN}-python = "${PYTHON_SITEPACKAGES_DIR}"
RRECOMMENDS_PYTHON = "${@bb.utils.contains('PACKAGECONFIG', 'python3', '${PN}-python', '',d)}"
RRECOMMENDS_${PN}-python += "${RRECOMMENDS_PYTHON}"
