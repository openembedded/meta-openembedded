SUMMARY = "passive checker of Python programs"
HOMEPAGE = "https://github.com/PyCQA/pyflakes"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=690c2d09203dc9e07c4083fc45ea981f"

SRC_URI[sha256sum] = "94762a3a5a343a79b28754f96c554bce057a592a4896907d73f0369fe824e053"

inherit pypi setuptools3

BBCLASSEXTEND = "native nativesdk"
