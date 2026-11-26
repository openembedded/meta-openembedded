SUMMARY = "A small Python module for determining appropriate platform-specific dirs"
HOMEPAGE = "https://github.com/platformdirs/platformdirs"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ea4f5a41454746a9ed111e3d8723d17a"

SRC_URI[sha256sum] = "96893d686e84e9e6f358a9ea41a8de544df66dbf2ba6cda250e7dc73acf85b9b"

inherit pypi python_hatchling ptest-python-pytest

SRC_URI += "file://run-ptest"

DEPENDS += " \
    python3-hatch-fancy-pypi-readme-native \
    python3-hatch-vcs-native \
    python3-python-sbom-native \
"

RDEPENDS:${PN}-ptest += " \
    python3-appdirs \
    python3-covdefaults \
    python3-pytest-cov \
    python3-pytest-mock \
"

BBCLASSEXTEND = "native nativesdk"
