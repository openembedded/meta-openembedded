SUMMARY = "Collection of plugins for markdown-it-py"
HOMEPAGE = "https://github.com/executablebooks/mdit-py-plugins"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a38a1697260a7ad7bf29f44b362db1fc"

SRC_URI[sha256sum] = "26d2179227aaf4d1dc7acffe7a738cbd15cade6b5a61cff0a2cbf9fed93efede"

inherit pypi python_flit_core

RDEPENDS:${PN} += "python3-markdown-it-py"

BBCLASSEXTEND = "native nativesdk"
