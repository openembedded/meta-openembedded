SUMMARY = "Python decorator utilities"
DESCRIPTION = "\
The aim of the decorator module it to simplify the usage of decorators \
for the average programmer, and to popularize decorators by showing \
various non-trivial examples. Of course, as all techniques, decorators \
can be abused and you should not try to solve every problem with a \
decorator, just because you can."

LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=fb3c386712bc6cfb52aae3692d1ebbd4"

SRC_URI[sha256sum] = "d9883f1b8259e3bfc42565d0bc6fdc760012008552aa449f75efd2d48319b82b"

inherit pypi python_setuptools_build_meta

RDEPENDS:${PN} += "\
    python3-stringold \
    "

BBCLASSEXTEND = "native nativesdk"
