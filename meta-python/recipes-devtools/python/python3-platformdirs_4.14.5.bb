SUMMARY = "A small Python module for determining appropriate platform-specific dirs"
HOMEPAGE = "https://github.com/platformdirs/platformdirs"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ea4f5a41454746a9ed111e3d8723d17a"

SRC_URI[sha256sum] = "32e9921db7385aa8169bee4fe9501923e5715ba9fb7ea3b9c69b0d8e0639f7a1"

inherit pypi python_hatchling ptest-python-pytest

SRC_URI += "file://run-ptest"

DEPENDS += " \
    python3-hatch-fancy-pypi-readme-native \
    python3-hatch-vcs-native \
"

RDEPENDS:${PN}-ptest += " \
    python3-appdirs \
    python3-covdefaults \
    python3-pytest-cov \
    python3-pytest-mock \
"

BBCLASSEXTEND = "native nativesdk"
