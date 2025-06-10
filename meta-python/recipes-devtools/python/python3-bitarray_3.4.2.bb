SUMMARY = "efficient arrays of booleans -- C extension"
DESCRIPTION = "A high-level Python efficient arrays of booleans -- C extension"
HOMEPAGE = "https://github.com/ilanschnell/bitarray"
LICENSE = "PSF-2.0"
LIC_FILES_CHKSUM = "file://PKG-INFO;beginline=8;endline=8;md5=6abe80c028e4ee53045a33ae807c64fd"

SRC_URI[sha256sum] = "78ed2b911aabede3a31e3329b1de8abdc8104bd5e0545184ddbd9c7f668f4059"

inherit python_setuptools_build_meta pypi

BBCLASSEXTEND = "native nativesdk"
