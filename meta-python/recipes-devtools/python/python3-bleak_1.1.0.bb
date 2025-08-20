SUMMARY = "Bleak is a GATT client software, capable of connecting to BLE devices acting as GATT servers."
HOMEPAGE = "https://github.com/hbldh/bleak"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=bcbc2069a86cba1b5e47253679f66ed7"

SRCREV = "edc9c5125614f816ac7e8edbf2b4338e3142fd3b"
PYPI_SRC_URI = "git://github.com/hbldh/bleak.git;protocol=https;branch=develop;destsuffix=${S}"

inherit pypi python_poetry_core ptest-python-pytest

RDEPENDS:${PN}-ptest += " \
	python3-asyncio \
	python3-pytest-asyncio \
	python3-pytest-cov \
"

RDEPENDS:${PN} += " \
	python3-core (>3.8) \
	python3-dbus-fast \
	python3-xml \
"
