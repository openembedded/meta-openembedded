SUMMARY = "A high-level Python efficient arrays of booleans -- C extension"
HOMEPAGE = "https://github.com/ilanschnell/bitarray"
LICENSE = "PSF-2.0"
LIC_FILES_CHKSUM = "file://PKG-INFO;beginline=8;endline=8;md5=2ad702cdcd49e8d2ac01d7e7d0810d2d"

SRC_URI[sha256sum] = "00bb723cf7059e30b328b6568b3b75c0f652ec9228d959d54e997852a31a31a2"

inherit setuptools3 pypi

BBCLASSEXTEND = "native nativesdk"
