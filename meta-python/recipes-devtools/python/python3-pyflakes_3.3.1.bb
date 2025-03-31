SUMMARY = "passive checker of Python programs"
HOMEPAGE = "https://github.com/PyCQA/pyflakes"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=690c2d09203dc9e07c4083fc45ea981f"

SRC_URI[sha256sum] = "8752eee11d4ef3a4be642d774863047864b47406cba906fabf8dd892cf98d5b3"

inherit pypi setuptools3

BBCLASSEXTEND = "native nativesdk"
