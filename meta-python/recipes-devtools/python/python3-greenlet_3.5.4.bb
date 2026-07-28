SUMMARY = "Python lightweight in-process concurrent programming"
HOMEPAGE = "https://greenlet.readthedocs.io/en/latest/"
LICENSE = "MIT AND PSF-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e95668d68e4329085c7ab3535e6a7aee \
                    file://LICENSE.PSF;md5=c106931d9429eda0492617f037b8f69a"

SRC_URI[sha256sum] = "0232ae1de90a8e07867bb127d7a6ba2301e859145489f25cda8a6096dabe1d20"

inherit pypi python_setuptools_build_meta

BBCLASSEXTEND = "native nativesdk"
