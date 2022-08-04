SUMMARY = "Python style guide checker (formly called pep8)"
HOMEPAGE = "https://pypi.org/project/pycodestyle"
LICENSE = "MIT"
SECTION = "devel/python"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a8546d0e77f416fb05a26acd89c8b3bd"

SRC_URI[sha256sum] = "beaba44501f89d785be791c9462553f06958a221d166c64e1f107320f839acc2"

inherit pypi setuptools3

BBCLASSEXTEND = "native nativesdk"
