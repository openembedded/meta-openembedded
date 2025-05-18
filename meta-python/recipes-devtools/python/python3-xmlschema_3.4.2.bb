SUMMARY = "The xmlschema library is an implementation of XML Schema for Python (supports Python 3.6+)."
HOMEPAGE = "https://github.com/sissaschool/xmlschema"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=26aa26eda991a3a2b61c11b62d3fda65"

SRC_URI[sha256sum] = "d35023ea504ea46127302d1297b046d023b96fec5fe4b4b690534ea85b5e9bf8"

inherit pypi python_setuptools_build_meta

RDEPENDS:${PN} += "\
    python3-elementpath \
    python3-modules \
"

BBCLASSEXTEND = "native nativesdk"
