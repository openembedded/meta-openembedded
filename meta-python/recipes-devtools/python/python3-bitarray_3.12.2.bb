SUMMARY = "efficient arrays of booleans -- C extension"
DESCRIPTION = "A high-level Python efficient arrays of booleans -- C extension"
HOMEPAGE = "https://github.com/ilanschnell/bitarray"
LICENSE = "PSF-2.0"
LIC_FILES_CHKSUM = "file://PKG-INFO;beginline=8;endline=8;md5=6abe80c028e4ee53045a33ae807c64fd"

SRC_URI[sha256sum] = "940b64a0701cea18c0698ef23ec2d9a038e47103b0402b19cfb298d71817d27b"

inherit python_setuptools_build_meta pypi

BBCLASSEXTEND = "native nativesdk"
