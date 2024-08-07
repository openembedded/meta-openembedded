SUMMARY = "A faster version of dbus-next originally from the great DBus next library."
HOMEPAGE = "https://github.com/bluetooth-devices/dbus-fast"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=729e372b5ea0168438e4fd4a00a04947"

SRC_URI[sha256sum] = "aa75dfb5bc7ba42f53391ae503ca5a21bd133e74ebb09965013ba23bdffc9a0e"

PYPI_PACKAGE = "dbus_fast"

inherit pypi python_poetry_core

DEPENDS += " \
	python3-setuptools-native \
	python3-wheel-native \
	python3-cython-native \
	"

RDEPENDS:${PN} += " \
    python3-core (>=3.7) \
    python3-async-timeout \
"
