require libgpiod.inc

DEPENDS += "autoconf-archive-native"

SRC_URI[md5sum] = "80237a047a9d653a14c5d71e5ce9d641"
SRC_URI[sha256sum] = "9758466468a7ef3f5e30c182c1303abef6241e665cda4d82a64328a7474838c1"

PACKAGECONFIG[cxx] = "--enable-bindings-cxx,--disable-bindings-cxx"

PACKAGECONFIG[python3] = "--enable-bindings-python,--disable-bindings-python,python3,python3-core"
inherit ${@bb.utils.contains('PACKAGECONFIG', 'python3', 'python3native', '', d)}

PACKAGES =+ "${PN}-python"
FILES_${PN}-python = "${PYTHON_SITEPACKAGES_DIR}"
RRECOMMENDS_PYTHON = "${@bb.utils.contains('PACKAGECONFIG', 'python3', '${PN}-python', '',d)}"
RRECOMMENDS_${PN} += "${RRECOMMENDS_PYTHON}"
