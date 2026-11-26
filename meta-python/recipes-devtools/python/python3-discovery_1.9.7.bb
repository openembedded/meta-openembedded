SUMMARY = "Python interpreter discovery"
HOMEPAGE = "https://github.com/tox-dev/python-discovery"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=11610a9d8fd95649cf8159be12b98cb7"

PYPI_PACKAGE = "python-discovery"

SRC_URI[sha256sum] = "cea89e8a8b86e19731c67f8393f34eb1a2f96c9f97f3e406929a99c646a2358d"

inherit pypi python_hatchling ptest-python-pytest

DEPENDS += "python3-hatch-fancy-pypi-readme-native python3-hatch-vcs-native python3-python-sbom-native"

RDEPENDS:${PN} += "\
    python3-core \
    python3-logging \
    python3-platformdirs \
"

RDEPENDS:${PN}-ptest += " \
    python3-setuptools \
    python3-filelock \
    python3-vermin \
"

BBCLASSEXTEND = "native nativesdk"
