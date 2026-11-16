SUMMARY = "Universal character encoding detector"
HOMEPAGE = "https://github.com/PyYoshi/cChardet"
LICENSE = "MPL-1.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=6ecda54f6f525388d71d6b3cd92f7474"

SRC_URI[sha256sum] = "3e3b0b9f11f24b66dc34997035e4d9c570594277e59c878ef004f666b3f45124"

inherit pypi python_setuptools_build_meta cython

BBCLASSEXTEND = "native nativesdk"
