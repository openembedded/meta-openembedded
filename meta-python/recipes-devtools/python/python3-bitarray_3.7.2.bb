SUMMARY = "efficient arrays of booleans -- C extension"
DESCRIPTION = "A high-level Python efficient arrays of booleans -- C extension"
HOMEPAGE = "https://github.com/ilanschnell/bitarray"
LICENSE = "PSF-2.0"
LIC_FILES_CHKSUM = "file://PKG-INFO;beginline=8;endline=8;md5=6abe80c028e4ee53045a33ae807c64fd"

SRC_URI[sha256sum] = "27a59bb7c64c0d094057a3536e15fdd693f8520771ee75d9344b82d0a5ade2d0"

inherit python_setuptools_build_meta pypi

BBCLASSEXTEND = "native nativesdk"
