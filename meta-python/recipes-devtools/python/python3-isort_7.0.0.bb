SUMMARY = "A Python utility / library to sort Python imports."
HOMEPAGE = "https://pypi.python.org/pypi/isort"
LICENSE = "MIT"
SECTION = "devel/python"
LIC_FILES_CHKSUM = "file://LICENSE;md5=197c46995787b46a2dbf0d519c1754cf"

SRC_URI[sha256sum] = "5513527951aadb3ac4292a41a16cbc50dd1642432f5e8c20057d414bdafb4187"

inherit pypi python_hatchling

DEPENDS += "python3-hatch-vcs-native python3-hatchling-native"

RDEPENDS:${PN} += " \
    python3-compression \
    python3-datetime \
    python3-difflib \
    python3-email \
    python3-numbers \
    python3-pprint \
    python3-profile \
    python3-shell \
"

BBCLASSEXTEND = "native nativesdk"
