SUMMARY = "efficient arrays of booleans -- C extension"
DESCRIPTION = "A high-level Python efficient arrays of booleans -- C extension"
HOMEPAGE = "https://github.com/ilanschnell/bitarray"
LICENSE = "PSF-2.0"
LIC_FILES_CHKSUM = "file://PKG-INFO;beginline=8;endline=8;md5=2ad702cdcd49e8d2ac01d7e7d0810d2d"

SRC_URI[sha256sum] = "a3c1d74ac2c969bac33169286fe601f8a6f4ca0e8f26dbaa22ad61fbf8fcf259"

inherit setuptools3 pypi

BBCLASSEXTEND = "native nativesdk"
