SUMMARY = "Simple construction, analysis and modification of binary data."
HOMEPAGE = "https://github.com/scott-griffiths/bitstring"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=661f450e2c0aef39b4b15597333444a7"

SRC_URI[sha256sum] = "b40b01d911eebaea6efff40d826580806dced5e04b9d3cbad6aebf9422f4b643"

PYPI_PACKAGE = "bitstring"

inherit pypi python_poetry_core

RDEPENDS:${PN} = "\
    python3-core \
    python3-io \
    python3-mmap \
    python3-numbers \
"

BBCLASSEXTEND = "native nativesdk"
