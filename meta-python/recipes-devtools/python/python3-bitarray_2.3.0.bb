SUMMARY = "A high-level Python efficient arrays of booleans -- C extension"
HOMEPAGE = "https://github.com/ilanschnell/bitarray"
LICENSE = "PSF"
LIC_FILES_CHKSUM = "file://PKG-INFO;beginline=8;endline=8;md5=2ad702cdcd49e8d2ac01d7e7d0810d2d"

SRC_URI[sha256sum] = "21f2e10420f9ae74d3bdfd3cb74947ddbe9fbc0b2e5662f2f039001954f1d8b6"

inherit setuptools3 pypi

BBCLASSEXTEND = "native nativesdk"
