SUMMARY = "A generator framework based on a common modern IDL"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=eee61e10a40b0e3045ee5965bcd9a8b5"

SRC_URI[sha256sum] = "8606fbade67c3c0afe8ed5bbe860e7ffcd523ce0568c3f9dbe9f04503542f3cf"

inherit pypi setuptools3

DEPS = "\
    python3-jinja2 \
    python3-click \
    python3-pyyaml \
    python3-pytest \
    python3-six \
    python3-path \
    python3-antlr4-runtime \
    python3-watchdog \
    python3-markupsafe \
    python3-setuptools \
"
DEPENDS += "${DEPS}"
RDEPENDS:${PN} += "${DEPS}"

BBCLASSEXTEND = "nativesdk native"
