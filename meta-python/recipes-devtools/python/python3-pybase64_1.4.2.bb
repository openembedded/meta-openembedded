SUMMARY = "Fast Base64 encoding/decoding in Python"
HOMEPAGE = "https://github.com/mayeut/pybase64"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=84b11fa55a5d83cf6fa202fd3b49c7e8"

inherit pypi python_setuptools_build_meta

SRC_URI[sha256sum] = "46cdefd283ed9643315d952fe44de80dc9b9a811ce6e3ec97fd1827af97692d0"

BBCLASSEXTEND = "native nativesdk"
