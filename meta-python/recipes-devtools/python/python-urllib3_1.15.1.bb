SUMMARY = "Python HTTP library with thread-safe connection pooling, file post support, sanity friendly, and more"
HOMEPAGE = "https://github.com/shazow/urllib3"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=ea114851ad9a8c311aac8728a681a067"

SRC_URI[md5sum] = "5be254b0dbb55d1307ede99e1895c8dd"
SRC_URI[sha256sum] = "d0a1dc60433f7e9b90b4f085f1d45753174b4594558b29eda0009abe0b82da4c"

inherit pypi setuptools

RDEPENDS_${PN} += "python-netclient"
