require libgpiod.inc

DEPENDS += "autoconf-archive-native"

SRC_URI[md5sum] = "b552bfa87685dfd728a1485e914bfae2"
SRC_URI[sha256sum] = "b289b70e3d63a8e06205f7445d5342f4742277c267ce24a7c4d0017fff339e41"

# enable cxx bindings
PACKAGECONFIG ?= "cxx"

PACKAGECONFIG[cxx] = "--enable-bindings-cxx,--disable-bindings-cxx"
PACKAGECONFIG[tests] = "--enable-tests,--disable-tests,kmod udev"

PACKAGECONFIG[python3] = "--enable-bindings-python,--disable-bindings-python,python3"

inherit python3native

PACKAGES =+ "${PN}-python"
FILES_${PN}-python = "${PYTHON_SITEPACKAGES_DIR}"
RRECOMMENDS_PYTHON = "${@bb.utils.contains('PACKAGECONFIG', 'python3', '${PN}-python', '',d)}"
RRECOMMENDS_${PN}-python += "${RRECOMMENDS_PYTHON}"
