SUMMARY = "CSS selectors for Python ElementTree."
DESCRIPTION = "cssselect2 is a straightforward implementation of CSS4 Selectors \
    for markup documents (HTML, XML, etc.) that can be read by ElementTree-like \
    parsers (including cElementTree, lxml, html5lib, etc.)"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=aa7228954285c7398bb6711fee73b4ac"

SRC_URI[sha256sum] = "7674ffb954a3b46162392aee2a3a0aedb2e14ecf99fcc28644900f4e6e3e9d3a"

inherit pypi python_setuptools_build_meta

BBCLASSEXTEND = "native nativesdk"

RDEPENDS:${PN} = "python3-tinycss2"
