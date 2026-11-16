SUMMARY = "An Enum that inherits from str"
HOMEPAGE = "https://github.com/irgeek/StrEnum"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ba0eb3de1df70bde0ed48488cfd81269"

DEPENDS = "python3-setuptools-scm-native"

SRC_URI[sha256sum] = "9c50a5ce647c0a625ac25c1b719039e745466b6a04bd001aee6376381d8cdce7"

inherit pypi python_setuptools_build_meta
