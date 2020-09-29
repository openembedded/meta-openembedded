SUMMARY = "Alternative regular expression module, to replace re."
HOMEPAGE = "https://bitbucket.org/mrabarnett/mrab-regex/src"
LICENSE = "PSF"
LIC_FILES_CHKSUM = "file://PKG-INFO;beginline=8;endline=8;md5=ee7987010dadc17745d623f406b500ec"

inherit pypi setuptools3

SRC_URI[md5sum] = "195f5e867e9f87a39f921ad0e5761a61"
SRC_URI[sha256sum] = "a6f32aea4260dfe0e55dc9733ea162ea38f0ea86aa7d0f77b15beac5bf7b369d"

BBCLASSEXTEND = "native nativesdk"
