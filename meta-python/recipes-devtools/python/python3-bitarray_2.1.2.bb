SUMMARY = "A high-level Python efficient arrays of booleans -- C extension"
HOMEPAGE = "https://github.com/ilanschnell/bitarray"
LICENSE = "PSF"
LIC_FILES_CHKSUM = "file://PKG-INFO;beginline=8;endline=8;md5=2ad702cdcd49e8d2ac01d7e7d0810d2d"

SRC_URI[sha256sum] = "8745782cb201295963edbd7073072198e001dbe5132718234860b853c901ef66"

inherit setuptools3 pypi

BBCLASSEXTEND = "native nativesdk"
