DESCRIPTION = "Random Strings in Python"
HOMEPAGE = "https://github.com/leapfrogonline/rstr"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=5c15c04f1cdbd42c6152066f0b02102a"

SRC_URI[sha256sum] = "1a1e7f304b0291edbc12955a9cb4093bfe1c7c1d48e0797e752bb3bf2a24574a"

inherit pypi python_setuptools_build_meta

DEPENDS += "python3-wheel-native"

DEPENDS += "python3-setuptools-scm-native"

BBCLASSEXTEND = "native nativesdk"
