SUMMARY = "efficient arrays of booleans -- C extension"
DESCRIPTION = "A high-level Python efficient arrays of booleans -- C extension"
HOMEPAGE = "https://github.com/ilanschnell/bitarray"
LICENSE = "PSF-2.0"
LIC_FILES_CHKSUM = "file://PKG-INFO;beginline=8;endline=8;md5=6abe80c028e4ee53045a33ae807c64fd"

SRC_URI[sha256sum] = "d8f8dbcda062ea59b3a6d5233b5a9b67f6bf58c1418ad8f418c5138361f9f068"

inherit python_setuptools_build_meta pypi

BBCLASSEXTEND = "native nativesdk"
