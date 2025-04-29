SUMMARY = "Python lightweight in-process concurrent programming"
HOMEPAGE = "https://greenlet.readthedocs.io/en/latest/"
LICENSE = "MIT & PSF-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e95668d68e4329085c7ab3535e6a7aee \
                    file://LICENSE.PSF;md5=c106931d9429eda0492617f037b8f69a"

SRC_URI[sha256sum] = "9f4dd4b4946b14bb3bf038f81e1d2e535b7d94f1b2a59fdba1293cd9c1a0a4d7"

inherit pypi python_setuptools_build_meta

BBCLASSEXTEND = "native nativesdk"
