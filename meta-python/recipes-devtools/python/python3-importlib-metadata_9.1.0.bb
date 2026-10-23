SUMMARY = "Read metadata from Python packages"
HOMEPAGE = "https://pypi.org/project/importlib-metadata/"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=affe5a7d8b988c3db245c01075b29e17"

inherit pypi python_setuptools_build_meta

SRC_URI[sha256sum] = "9607fb24f95d4e0b1c17d57ec4604357d76d3f0d154f19fd4861f2e31c8e4065"

S = "${UNPACKDIR}/importlib_metadata-${PV}"

DEPENDS += "python3-setuptools-scm-native python3-coherent-licensed-native"
RDEPENDS:${PN} += "python3-zipp"
RDEPENDS:${PN}:append:class-target = " python3-misc"
RDEPENDS:${PN}:append:class-nativesdk = " python3-misc"

BBCLASSEXTEND = "native nativesdk"
