SUMMARY = "efficient arrays of booleans -- C extension"
DESCRIPTION = "A high-level Python efficient arrays of booleans -- C extension"
HOMEPAGE = "https://github.com/ilanschnell/bitarray"
LICENSE = "PSF-2.0"
LIC_FILES_CHKSUM = "file://PKG-INFO;beginline=8;endline=8;md5=6abe80c028e4ee53045a33ae807c64fd"

SRC_URI[sha256sum] = "20febc849a1f858e6a57a7d47b323fe9e727c579ddd526d317ad8831748a66a8"

inherit python_setuptools_build_meta pypi

BBCLASSEXTEND = "native nativesdk"
