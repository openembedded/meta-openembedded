SUMMARY = "Python decorator utilities"
HOMEPAGE = "http://pypi.python.org/pypi/decorator/"
DESCRIPTION = "\
The aim of the decorator module it to simplify the usage of decorators \
for the average programmer, and to popularize decorators by showing \
various non-trivial examples. Of course, as all techniques, decorators \
can be abused and you should not try to solve every problem with a \
decorator, just because you can."

SECTION = "devel/python"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://documentation3.py;beginline=848;endline=877;md5=0b56661417fb7b841afc8892e14ba241"

SRCNAME = "decorator"

SRC_URI = "https://pypi.python.org/packages/source/d/decorator/decorator-${PV}.tar.gz"
S = "${WORKDIR}/${SRCNAME}-${PV}"

inherit setuptools

SRC_URI[md5sum] = "1e8756f719d746e2fc0dd28b41251356"
SRC_URI[sha256sum] = "c20b404cbb7ee5cebd506688e0114e3cd76f5ce233805a51f36e1a7988d9d783"
