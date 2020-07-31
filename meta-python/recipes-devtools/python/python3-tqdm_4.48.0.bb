SUMMARY = "Fast, Extensible Progress Meter"
HOMEPAGE = "http://tqdm.github.io/"
SECTION = "devel/python"

LICENSE = "MIT & MPL-2.0"
LIC_FILES_CHKSUM = "file://LICENCE;md5=7ea57584e3f8bbde2ae3e1537551de25"

SRC_URI[md5sum] = "89dbe66a10f78900e4e7be729ddd28ca"
SRC_URI[sha256sum] = "6baa75a88582b1db6d34ce4690da5501d2a1cb65c34664840a456b2c9f794d29"

inherit pypi setuptools3

BBCLASSEXTEND = "native nativesdk"
