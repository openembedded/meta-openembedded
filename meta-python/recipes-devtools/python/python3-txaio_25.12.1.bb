DESCRIPTION = "Compatibility API between asyncio/Twisted/Trollius"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=588502cb4ffc65da2b26780d6baa5a40"

SRC_URI[sha256sum] = "f0e83ffaf80a9b91bd710075cbc2bb71e879f8aa061b09f6e9dc122343dd69f2"

inherit pypi python_setuptools_build_meta

RDEPENDS:${PN} += " \
    python3-twisted \
"
