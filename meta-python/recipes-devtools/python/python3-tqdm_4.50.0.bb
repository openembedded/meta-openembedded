SUMMARY = "Fast, Extensible Progress Meter"
HOMEPAGE = "http://tqdm.github.io/"
SECTION = "devel/python"

LICENSE = "MIT & MPL-2.0"
LIC_FILES_CHKSUM = "file://LICENCE;md5=7ea57584e3f8bbde2ae3e1537551de25"

SRC_URI[md5sum] = "4352d89e2c6d6b017f40f244baddfc51"
SRC_URI[sha256sum] = "93b7a6a9129fce904f6df4cf3ae7ff431d779be681a95c3344c26f3e6c09abfa"

inherit pypi setuptools3

BBCLASSEXTEND = "native nativesdk"
