SUMMARY = "Python port of markdown-it. Markdown parsing, done right!"
HOMEPAGE = "https://github.com/executablebooks/markdown-it-py"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a38a1697260a7ad7bf29f44b362db1fc"

SRC_URI[sha256sum] = "91a58fa5b72c71a40cf7177250fd767480d7a2bb3ac426ccfb76fb90305fdaaa"

inherit pypi python_flit_core

RDEPENDS:${PN} += "python3-mdurl"

CVE_PRODUCT = "markdown-it-py"

BBCLASSEXTEND = "native nativesdk"
