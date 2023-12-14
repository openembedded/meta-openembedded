SUMMARY = "efficient arrays of booleans -- C extension"
DESCRIPTION = "A high-level Python efficient arrays of booleans -- C extension"
HOMEPAGE = "https://github.com/ilanschnell/bitarray"
LICENSE = "PSF-2.0"
LIC_FILES_CHKSUM = "file://PKG-INFO;beginline=8;endline=8;md5=2ad702cdcd49e8d2ac01d7e7d0810d2d"

SRC_URI[sha256sum] = "b7564fd218cc4479f7f0106d341e096f78907b47865aeeff702c807df1927c01"

inherit setuptools3 pypi

BBCLASSEXTEND = "native nativesdk"
