SUMMARY = "A fast implementation of the Cassowary constraint solver"
HOMEPAGE = "https://github.com/nucleic/kiwi"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://setup.py;endline=7;md5=e54bd74bd9d0a84ae3f8f6d21ada0ab4"

SRC_URI[md5sum] = "9d1b3e2dd2bbe1a81ffedcc73cab07e0"
SRC_URI[sha256sum] = "14f81644e1f3bf01fbc8b9c990a7889e9bb4400c4d0ff9155aa0bdd19cce24a9"

inherit pypi setuptools3

DEPENDS += "\
    python3-cppy-native \
"

RDEPENDS_${PN} += "\
    python3-core \
    python3-setuptools \
"

BBCLASSEXTEND = "native nativesdk"
