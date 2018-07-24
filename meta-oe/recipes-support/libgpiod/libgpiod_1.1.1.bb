require libgpiod.inc

DEPENDS += "autoconf-archive-native"

SRC_URI[md5sum] = "e5e946cb01a35e5046a1a7a108d6a96d"
SRC_URI[sha256sum] = "172fa1544ecb51f37533b3e67862298d50c0a5cc84975f3c0706dc15467f0dfd"

PACKAGECONFIG[cxx] = "--enable-bindings-cxx,--disable-bindings-cxx"

PACKAGECONFIG[python3] = "--enable-bindings-python,--disable-bindings-python,python3,python3-core"
inherit ${@bb.utils.contains('PACKAGECONFIG', 'python3', 'python3native', '', d)}

PACKAGES =+ "${PN}-python"
FILES_${PN}-python = "${PYTHON_SITEPACKAGES_DIR}"
RRECOMMENDS_PYTHON = "${@bb.utils.contains('PACKAGECONFIG', 'python3', '${PN}-python', '',d)}"
RRECOMMENDS_${PN} += "${RRECOMMENDS_PYTHON}"
