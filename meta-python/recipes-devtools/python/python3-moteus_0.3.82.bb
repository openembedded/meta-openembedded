SUMMARY = "moteus brushless controller library and tools"
HOMEPAGE = "https://github.com/mjbots/moteus"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://setup.py;beginline=3;endline=9;md5=24025d3c660abfc62a83f0e709a45e76"

inherit pypi setuptools3

SRC_URI[sha256sum] = "88f76d6901312ba044240f038a97f54fff463221888ca89e2c2bdc7e1679bd5d"

S = "${WORKDIR}/moteus-${PV}"

RDEPENDS:${PN} += "\
    python3-can \
    python3-importlib-metadata \
    python3-pyelftools \
    python3-pyserial \
"
