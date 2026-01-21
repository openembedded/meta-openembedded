SUMMARY = "moteus brushless controller library and tools"
HOMEPAGE = "https://github.com/mjbots/moteus"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://setup.py;beginline=3;endline=9;md5=24025d3c660abfc62a83f0e709a45e76"

inherit pypi setuptools3

SRC_URI[sha256sum] = "ef6c628bf64d56e30aac1f8e314f74b1bbd9875e5ec21ecd3c2ce9436f7e3644"

S = "${UNPACKDIR}/moteus-${PV}"

RDEPENDS:${PN} += "\
    python3-can \
    python3-importlib-metadata \
    python3-pyelftools \
    python3-pyserial \
"
