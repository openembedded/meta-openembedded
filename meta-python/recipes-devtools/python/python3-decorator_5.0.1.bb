SUMMARY = "Python decorator utilities"
DESCRIPTION = "\
The aim of the decorator module it to simplify the usage of decorators \
for the average programmer, and to popularize decorators by showing \
various non-trivial examples. Of course, as all techniques, decorators \
can be abused and you should not try to solve every problem with a \
decorator, just because you can."

LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=be2fd2007972bf96c08af3293d728b22"

SRC_URI[sha256sum] = "1e53162e016f317a61d93848f00e80e7109ca9ed06846c7f2930cf0ebede7c6c"

inherit pypi setuptools3

RDEPENDS_${PN} += "\
    ${PYTHON_PN}-stringold \
    "
