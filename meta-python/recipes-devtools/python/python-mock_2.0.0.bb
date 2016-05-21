SUMMARY = "A Python Mocking and Patching Library for Testing"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=de9dfbf780446b18aab11f00baaf5b7e"

SRC_URI[md5sum] = "0febfafd14330c9dcaa40de2d82d40ad"
SRC_URI[sha256sum] = "b158b6df76edd239b8208d481dc46b6afd45a846b7812ff0ce58971cf5bc8bba"
PYPI_PACKAGE_HASH = "0c53014354fc93c591ccc4abff12c473ad565a2eb24dcd82490fae33dbf2539f"

inherit pypi setuptools

RDEPENDS_${PN} += " \
    python-prettytable \
    python-cmd2 \
    python-pyparsing \
    python-mccabe \
    python-pep8 \
    python-pyflakes"
