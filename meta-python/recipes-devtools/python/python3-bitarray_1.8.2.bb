SUMMARY = "A high-level Python efficient arrays of booleans -- C extension"
HOMEPAGE = "https://github.com/ilanschnell/bitarray"
LICENSE = "PSF"
LIC_FILES_CHKSUM = "file://PKG-INFO;beginline=8;endline=8;md5=2ad702cdcd49e8d2ac01d7e7d0810d2d"

SRC_URI[sha256sum] = "96136d9add2b9b3b73e1eb7538b8d12547ee82dc3fbd31addef829371ce2a1bd"

inherit setuptools3 pypi

BBCLASSEXTEND = "native nativesdk"
