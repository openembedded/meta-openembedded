SUMMARY = "Pytest plugin to create CodSpeed benchmarks"
HOMEPAGE = "https://codspeed.io/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2856cbe664e8843cd4fd4c1d1d85c2c3"

DEPENDS = "python3-hatchling-native"
SRC_URI[sha256sum] = "0adc24baf01c64a6ca0a0b83b3cd704351708997e09ec086b7776c32227d4e0a"

inherit pypi python_hatchling

PYPI_PACKAGE = "pytest_codspeed"
RDEPENDS:${PN} = "python3-cffi python3-filelock python3-pytest"
