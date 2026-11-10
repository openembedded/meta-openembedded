SUMMARY = "moteus brushless controller library and tools"
HOMEPAGE = "https://github.com/mjbots/moteus"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://PKG-INFO;beginline=6;endline=6;md5=c2d9643b4523fdf462545aeb1356ad23"

inherit pypi python_setuptools_build_meta

DEPENDS += "python3-wheel-native"

SRC_URI[sha256sum] = "fb7babcde6293927ded01bcc79f75138412e4d2f18d149d421a9d71bfb6d6387"

S = "${UNPACKDIR}/moteus-${PV}"

RDEPENDS:${PN} += "\
    python3-can \
    python3-importlib-metadata \
    python3-pyelftools \
    python3-pyserial \
"
