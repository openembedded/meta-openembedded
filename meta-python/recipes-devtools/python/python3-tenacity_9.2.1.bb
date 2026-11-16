SUMMARY = "Retry code until it succeeds"
HOMEPAGE = "https://github.com/jd/tenacity"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=175792518e4ac015ab6696d16c4f607e"

SRC_URI[sha256sum] = "a606b5c808d0cded4a359d5b9932d867ff2a6a6b64d37350260fd01bbdf83839"

SRC_URI:append = " file://0001-ptest-skip-a-test-that-does-not-pass-on-qemu.patch"

DEPENDS += "python3-hatch-vcs-native"

inherit pypi python_hatchling ptest-python-pytest


RDEPENDS:${PN}-ptest += "\
    python3-tornado \
    python3-typeguard \
"
