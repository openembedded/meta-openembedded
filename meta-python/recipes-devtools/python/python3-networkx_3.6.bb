DESCRIPTION = "Python package for creating and manipulating graphs and networks"
HOMEPAGE = "http://networkx.github.io/"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=f7592b173aee2da0e062f9cfa0378e9d"

SRC_URI[sha256sum] = "285276002ad1f7f7da0f7b42f004bcba70d381e936559166363707fdad3d72ad"

inherit pypi python_setuptools_build_meta

RDEPENDS:${PN} += "\
                   python3-decorator \
                   python3-netclient \
                   python3-compression \
                   python3-numbers \
                   python3-pickle \
                   python3-html \
                   python3-xml \
                   python3-json \
                   python3-profile \
                   python3-threading \
                   "

BBCLASSEXTEND = "native nativesdk"
