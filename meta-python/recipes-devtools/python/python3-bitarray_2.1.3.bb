SUMMARY = "A high-level Python efficient arrays of booleans -- C extension"
HOMEPAGE = "https://github.com/ilanschnell/bitarray"
LICENSE = "PSF"
LIC_FILES_CHKSUM = "file://PKG-INFO;beginline=8;endline=8;md5=2ad702cdcd49e8d2ac01d7e7d0810d2d"

SRC_URI[sha256sum] = "a24aff72a7f1b09571b5daf9dbfcffd98481be1fe085ae5ef662cf11452a97e0"

inherit setuptools3 pypi

BBCLASSEXTEND = "native nativesdk"
