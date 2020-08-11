SUMMARY = "A high-level Python efficient arrays of booleans -- C extension"
HOMEPAGE = "https://github.com/ilanschnell/bitarray"
LICENSE = "PSF"
LIC_FILES_CHKSUM = "file://PKG-INFO;beginline=8;endline=8;md5=2ad702cdcd49e8d2ac01d7e7d0810d2d"

SRC_URI[md5sum] = "7005b90013838954facbaa231d7cccd9"
SRC_URI[sha256sum] = "45bba08bc142781ec7e18a847735219390808f9b6279c356252edddaee1f5fcd"

inherit setuptools3 pypi

BBCLASSEXTEND = "native nativesdk"
