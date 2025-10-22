SUMMARY = "The xmlschema library is an implementation of XML Schema for Python."
HOMEPAGE = "https://github.com/sissaschool/xmlschema"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=26aa26eda991a3a2b61c11b62d3fda65"

SRC_URI[sha256sum] = "b1f88c53493b2e75471977cbf218d939b872d0c7046bb63d48cc219fa7e241b9"

inherit pypi python_setuptools_build_meta

RDEPENDS:${PN} += "\
    python3-elementpath \
    python3-modules \
"

BBCLASSEXTEND = "native nativesdk"
