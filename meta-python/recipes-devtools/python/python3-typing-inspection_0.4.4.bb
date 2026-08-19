SUMMARY = "Runtime typing introspection tools"
HOMEPAGE = "https://github.com/pydantic/typing-inspection"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=dfe2d84c58973d6a532c4e7638dbb3d8"

DEPENDS = "python3-hatchling-native"

inherit pypi python_hatchling ptest-python-pytest
SRC_URI[sha256sum] = "547274fa6b0a561ccf549cc9524b999a578e737d015d8709d021f9d0d13bea47"

RDEPENDS:${PN}-ptest += "python3-typing-extensions"

PYPI_PACKAGE = "typing_inspection"

BBCLASSEXTEND += "native nativesdk"
