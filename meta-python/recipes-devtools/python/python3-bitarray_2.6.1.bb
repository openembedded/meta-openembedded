SUMMARY = "A high-level Python efficient arrays of booleans -- C extension"
HOMEPAGE = "https://github.com/ilanschnell/bitarray"
LICENSE = "PSF-2.0"
LIC_FILES_CHKSUM = "file://PKG-INFO;beginline=8;endline=8;md5=2ad702cdcd49e8d2ac01d7e7d0810d2d"

SRC_URI[sha256sum] = "8440a5493221f6ed6c4e0d9cb2ba6e4e600a5fb639f66003eaf15b7150a49230"

inherit setuptools3 pypi

BBCLASSEXTEND = "native nativesdk"
