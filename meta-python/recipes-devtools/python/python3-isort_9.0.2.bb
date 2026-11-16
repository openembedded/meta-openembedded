SUMMARY = "A Python utility / library to sort Python imports."
HOMEPAGE = "https://pypi.python.org/pypi/isort"
LICENSE = "MIT"
SECTION = "devel/python"
LIC_FILES_CHKSUM = "file://LICENSE;md5=197c46995787b46a2dbf0d519c1754cf"

SRC_URI[sha256sum] = "d2298980ce44350f11d9d24c8150eaef1883431ec203dddbb4e9b5c3ceb54c70"

inherit pypi python_hatchling

DEPENDS += "python3-hatch-vcs-native python3-hatchling-native"

# The mypyc build hook is disabled by default; don't require its plugin
do_configure:prepend() {
    sed -i -e '/^requires = /s/, "hatch-mypyc"//' ${S}/pyproject.toml
}

RDEPENDS:${PN} += " \
    python3-compression \
    python3-datetime \
    python3-difflib \
    python3-email \
    python3-mypy-extensions \
    python3-numbers \
    python3-pprint \
    python3-profile \
    python3-shell \
"

BBCLASSEXTEND = "native nativesdk"
