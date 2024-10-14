SUMMARY = "A faster version of dbus-next originally from the great DBus next library."
HOMEPAGE = "https://github.com/bluetooth-devices/dbus-fast"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=729e372b5ea0168438e4fd4a00a04947"

SRC_URI[sha256sum] = "9042a1b565ecac4f8e04df79376de1d1d31e4c82eddb6e71e8b8d82d0c94dd3d"

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
