SUMMARY = "efficient arrays of booleans -- C extension"
DESCRIPTION = "A high-level Python efficient arrays of booleans -- C extension"
HOMEPAGE = "https://github.com/ilanschnell/bitarray"
LICENSE = "PSF-2.0"
LIC_FILES_CHKSUM = "file://PKG-INFO;beginline=8;endline=8;md5=6abe80c028e4ee53045a33ae807c64fd"

SRC_URI[sha256sum] = "796f2b4f0e4d84df50bd23ddfacb37d74eec2b2366813cb8f18bdae6b25e3d36"

inherit python_setuptools_build_meta pypi

BBCLASSEXTEND = "native nativesdk"
