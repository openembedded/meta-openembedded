SUMMARY = "Read metadata from Python packages"
HOMEPAGE = "https://pypi.org/project/importlib-metadata/"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=affe5a7d8b988c3db245c01075b29e17"

inherit pypi python_setuptools_build_meta

SRC_URI[sha256sum] = "9413a2029fd9ca5669d27f1386071bbfe3c7ebca0545692ec975088241fe1d79"

DEPENDS += "python3-setuptools-scm-native python3-coherent-licensed-native"
RDEPENDS:${PN} += "python3-misc python3-zipp"

BBCLASSEXTEND = "native nativesdk"
