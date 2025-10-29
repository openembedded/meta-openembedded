DESCRIPTION = "An implementation of RFC 7049 - Concise Binary Object Representation (CBOR)."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=a79e64179819c7ce293372c059f1dbd8"
DEPENDS += "python3-setuptools-scm-native"

SRC_URI[sha256sum] = "7a405a1d7c8230ee9acf240aad48ae947ef584e8af05f169f3c1bde8f01f8b71"

inherit pypi python_setuptools_build_meta ptest-python-pytest

RDEPENDS:${PN}-ptest += " \
    python3-hypothesis \
    python3-unixadmin \
"
RDEPENDS:${PN} += " \
    python3-datetime \
"

BBCLASSEXTEND = "native nativesdk"
