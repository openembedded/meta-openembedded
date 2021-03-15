SUMMARY = "A high-level Python efficient arrays of booleans -- C extension"
HOMEPAGE = "https://github.com/ilanschnell/bitarray"
LICENSE = "PSF"
LIC_FILES_CHKSUM = "file://PKG-INFO;beginline=8;endline=8;md5=2ad702cdcd49e8d2ac01d7e7d0810d2d"

SRC_URI[sha256sum] = "e4de977d708b7024760266d827b8285e4405dce4293f25508c4556970139018a"

inherit setuptools3 pypi

BBCLASSEXTEND = "native nativesdk"
