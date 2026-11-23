SUMMARY = "Mypyc runtime library"
DESCRIPTION = "Efficient C implementations of various Python standard \
library classes and functions, used by mypyc compiled code and by mypy."
HOMEPAGE = "https://github.com/mypyc/librt"
LICENSE = "MIT AND PSF-2.0 AND BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=42baf4796d8073a1896b15d602af78f6"

SRC_URI[sha256sum] = "fd18d1f708f78577556a7d76505048b9e3ad6ff61b8d9c91616a5a3f1e6c770b"

inherit pypi python_setuptools_build_meta

RDEPENDS:${PN} += "python3-mypy-extensions"

BBCLASSEXTEND = "native nativesdk"
