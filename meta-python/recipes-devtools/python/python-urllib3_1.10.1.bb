SUMMARY = "Python HTTP library with thread-safe connection pooling, file post support, sanity friendly, and more"
HOMEPAGE = "https://github.com/shazow/urllib3"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=3be3707c5f24a69709682265e29566fe"

SRC_URI = "https://github.com/shazow/urllib3/archive/${PV}.zip"
SRC_URI[md5sum] = "73935fe42b6ef51d078edbbca7c1bd55"
SRC_URI[sha256sum] = "5fadd55b46bf826e46a482bb3d53662a8a88d8f9ea8656e351b659b8ad9bfc0f"

S = "${WORKDIR}/urllib3-${PV}"

inherit setuptools

RDEPENDS_${PN} += "python-netclient"
