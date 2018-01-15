SUMMARY = "Python decorator utilities"
DESCRIPTION = "\
The aim of the decorator module it to simplify the usage of decorators \
for the average programmer, and to popularize decorators by showing \
various non-trivial examples. Of course, as all techniques, decorators \
can be abused and you should not try to solve every problem with a \
decorator, just because you can."

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=893f896413826096ce5270c700a498fd"

SRC_URI[md5sum] = "e886337e9f70e70d45d67266b8e66044"
SRC_URI[sha256sum] = "7d46dd9f3ea1cf5f06ee0e4e1277ae618cf48dfb10ada7c8427cd46c42702a0e"

inherit pypi

RDEPENDS_${PN} += "\
    ${PYTHON_PN}-stringold \
    "
