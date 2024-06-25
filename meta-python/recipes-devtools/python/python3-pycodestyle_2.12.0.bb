SUMMARY = "Python style guide checker (formly called pep8)"
HOMEPAGE = "https://pypi.org/project/pycodestyle"
LICENSE = "MIT"
SECTION = "devel/python"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a8546d0e77f416fb05a26acd89c8b3bd"

SRC_URI[sha256sum] = "442f950141b4f43df752dd303511ffded3a04c2b6fb7f65980574f0c31e6e79c"

inherit pypi setuptools3

BBCLASSEXTEND = "native nativesdk"
