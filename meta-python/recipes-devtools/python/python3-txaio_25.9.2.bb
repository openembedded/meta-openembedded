DESCRIPTION = "Compatibility API between asyncio/Twisted/Trollius"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=588502cb4ffc65da2b26780d6baa5a40"

SRC_URI[sha256sum] = "e42004a077c02eb5819ff004a4989e49db113836708430d59cb13d31bd309099"

inherit pypi python_setuptools_build_meta

RDEPENDS:${PN} += " \
    python3-twisted \
"
