DESCRIPTION = "A colored formatter for the python logging module"
HOMEPAGE = "https://github.com/borntyping/python-colorlog"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5c3c6ebdec7792ae12df8d1c0a46b26a"

inherit pypi setuptools3

PYPI_PACKAGE = "colorlog"

SRC_URI[sha256sum] = "9d90fb53fa906c8970c18fbe46506bae1fb5f86b513b8f867db37e4ace9be7ae"

RDEPENDS:${PN} += "python3-logging"

BBCLASSEXTEND += "native nativesdk"
