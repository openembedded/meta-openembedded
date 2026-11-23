SUMMARY = "Pure python implementation of magic file detection"
DESCRIPTION = "puremagic identifies a file by its magic numbers, like \
libmagic, but in pure Python without external dependencies."
HOMEPAGE = "https://github.com/cdgriffith/puremagic"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ac85ec1f210835a5806bc00df0be30d7"

SRC_URI[sha256sum] = "b54e4a69dd37c50bacc879380f132382ab4e9a935529628c8975abcc73e10640"

inherit pypi python_setuptools_build_meta

DEPENDS += "python3-setuptools-scm-native"

RDEPENDS:${PN} += " \
    python3-compression \
    python3-core \
    python3-json \
    python3-netclient \
"

BBCLASSEXTEND = "native nativesdk"
