SUMMARY = "efficient arrays of booleans -- C extension"
DESCRIPTION = "A high-level Python efficient arrays of booleans -- C extension"
HOMEPAGE = "https://github.com/ilanschnell/bitarray"
LICENSE = "PSF-2.0"
LIC_FILES_CHKSUM = "file://PKG-INFO;beginline=8;endline=8;md5=6abe80c028e4ee53045a33ae807c64fd"

SRC_URI[sha256sum] = "08a86f85fd0534da3b753f072c7b0d392d4c0c9418fe2a358be378152cf6b085"

inherit python_setuptools_build_meta pypi

BBCLASSEXTEND = "native nativesdk"
