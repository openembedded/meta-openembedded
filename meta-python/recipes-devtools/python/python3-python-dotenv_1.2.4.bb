SUMMARY = "Read key-value pairs from a .env file and set them as environment variables"
HOMEPAGE = "https://github.com/theskumar/python-dotenv"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e914cdb773ae44a732b392532d88f072"
RECIPE_MAINTAINER = "Tom Geelen <t.f.g.geelen@gmail.com>"

SRC_URI[sha256sum] = "f0d53e69935a851c0dcc78f3ab7aaccd8cabef0b92382b576b824212902873c0"


inherit pypi python_setuptools_build_meta ptest-python-pytest

# Uncomment this line to enable all the optional features.
PACKAGECONFIG ?= ""
PACKAGECONFIG:append = "${@bb.utils.contains('PTEST_ENABLED', '1', ' cli', '', d)}"
PACKAGECONFIG[cli] = ",,,python3-click"

RDEPENDS:${PN}-ptest += "\
    coreutils \
    python3-sh \
"
