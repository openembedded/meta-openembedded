SUMMARY = "A high-level Python efficient arrays of booleans -- C extension"
HOMEPAGE = "https://github.com/ilanschnell/bitarray"
LICENSE = "PSF"
LIC_FILES_CHKSUM = "file://PKG-INFO;beginline=8;endline=8;md5=2ad702cdcd49e8d2ac01d7e7d0810d2d"

SRC_URI[md5sum] = "2bc9489e9048db6a8705a7b9493e3015"
SRC_URI[sha256sum] = "16f9908c9388af616f2b298febbefa8315d648f522d195a7706d8ba1895c5680"

inherit setuptools3 pypi

BBCLASSEXTEND = "native nativesdk"
