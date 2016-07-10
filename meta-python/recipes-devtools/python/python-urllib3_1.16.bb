SUMMARY = "Python HTTP library with thread-safe connection pooling, file post support, sanity friendly, and more"
HOMEPAGE = "https://github.com/shazow/urllib3"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=ea114851ad9a8c311aac8728a681a067"

SRC_URI[md5sum] = "fcaab1c5385c57deeb7053d3d7d81d59"
SRC_URI[sha256sum] = "63d479478ddfc83bbc11577dc16d47835c5179ac13e550118ca143b62c4bf9ab"

inherit pypi setuptools

RDEPENDS_${PN} += "python-netclient"
