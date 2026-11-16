SUMMARY = "A python package implementing the crc32c algorithmin hardware and software"
HOMEPAGE = "https://github.com/ICRAR/crc32c"

LICENSE = "BSD-2-Clause AND BSD-3-Clause AND LGPL-2.1-or-later AND Zlib"
LIC_FILES_CHKSUM = " \
    file://LICENSE;md5=4fbd65380cdd255951079008b364516c \
    file://LICENSE.google-crc32c;md5=e9ed01b5e5ac9eae23fc2bb33701220c \
    file://LICENSE.slice-by-8;md5=6b3bc7709d6b2db6646ec2467310ff6b \
    file://src/crc32c/ext/crc32c_adler.c;beginline=9;endline=24;md5=9c8bd2afd2d340fd37c038759cd4eff8 \
"

SRC_URI[sha256sum] = "6a089e0340de8438e836a09e613c6b541675d0f3aa92b3fe34295aaba62f014f"

inherit pypi python_setuptools_build_meta

RDEPENDS:${PN} += "python3-core"
