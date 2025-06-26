SUMMARY = "moteus brushless controller library and tools"
HOMEPAGE = "https://github.com/mjbots/moteus"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://setup.py;beginline=3;endline=9;md5=24025d3c660abfc62a83f0e709a45e76"

inherit pypi setuptools3

SRC_URI[sha256sum] = "e16387e5fe512a7580ef847e6b2e665d4b40549b064ab237e01fd7dec7c46232"

S = "${UNPACKDIR}/moteus-${PV}"

RDEPENDS:${PN} += "\
    python3-can \
    python3-importlib-metadata \
    python3-pyelftools \
    python3-pyserial \
"
