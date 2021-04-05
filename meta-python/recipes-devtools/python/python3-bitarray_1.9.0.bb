SUMMARY = "A high-level Python efficient arrays of booleans -- C extension"
HOMEPAGE = "https://github.com/ilanschnell/bitarray"
LICENSE = "PSF"
LIC_FILES_CHKSUM = "file://PKG-INFO;beginline=8;endline=8;md5=2ad702cdcd49e8d2ac01d7e7d0810d2d"

SRC_URI[sha256sum] = "db00073387af7fe30852d96b04742a0cdbc7feaf3158558520ab7898b0435ee2"

inherit setuptools3 pypi

BBCLASSEXTEND = "native nativesdk"
