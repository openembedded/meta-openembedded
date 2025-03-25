SUMMARY = "A faster version of dbus-next originally from the great DBus next library."
HOMEPAGE = "https://github.com/bluetooth-devices/dbus-fast"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=729e372b5ea0168438e4fd4a00a04947"

SRC_URI[sha256sum] = "fc3349ffea522d137d856f8967129dd4b87394c0bf8b8b1648295c5eb0a457fe"

PYPI_PACKAGE = "dbus_fast"
UPSTREAM_CHECK_PYPI_PACKAGE = "${PYPI_PACKAGE}"

inherit pypi python_poetry_core cython

RDEPENDS:${PN} += " \
    python3-core (>=3.7) \
    python3-async-timeout \
"
