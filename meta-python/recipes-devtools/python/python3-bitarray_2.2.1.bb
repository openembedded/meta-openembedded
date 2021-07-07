SUMMARY = "A high-level Python efficient arrays of booleans -- C extension"
HOMEPAGE = "https://github.com/ilanschnell/bitarray"
LICENSE = "PSF"
LIC_FILES_CHKSUM = "file://PKG-INFO;beginline=8;endline=8;md5=2ad702cdcd49e8d2ac01d7e7d0810d2d"

SRC_URI[sha256sum] = "31b40d716a1f0642ea9e2741c29b756299075db2e1d1ebe750e3e2c1469f589d"

inherit setuptools3 pypi

BBCLASSEXTEND = "native nativesdk"
