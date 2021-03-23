SUMMARY = "A high-level Python efficient arrays of booleans -- C extension"
HOMEPAGE = "https://github.com/ilanschnell/bitarray"
LICENSE = "PSF"
LIC_FILES_CHKSUM = "file://PKG-INFO;beginline=8;endline=8;md5=2ad702cdcd49e8d2ac01d7e7d0810d2d"

SRC_URI[sha256sum] = "fe4444d92b17073bf1f9f24e3015a0e5bb70a645c47df93ef8a9ce8be33fcbad"

inherit setuptools3 pypi

BBCLASSEXTEND = "native nativesdk"
