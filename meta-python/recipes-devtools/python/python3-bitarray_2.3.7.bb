SUMMARY = "A high-level Python efficient arrays of booleans -- C extension"
HOMEPAGE = "https://github.com/ilanschnell/bitarray"
LICENSE = "PSF"
LIC_FILES_CHKSUM = "file://PKG-INFO;beginline=8;endline=8;md5=2ad702cdcd49e8d2ac01d7e7d0810d2d"

SRC_URI[sha256sum] = "4da790ab193e993107f3ff0aebc4f8d6ad5196a19071fbafad16b02898a44fd7"

inherit setuptools3 pypi

# Backported from 2.3.8
SRC_URI += "file://setup.py-use-setuptools-instead-of-distutils.patch"

BBCLASSEXTEND = "native nativesdk"
