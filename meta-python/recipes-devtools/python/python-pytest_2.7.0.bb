SUMMARY = "Simple powerful teting with Python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a6bb0320b04a0a503f12f69fea479de9"

SRC_URI[md5sum] = "14cde91b1106c937c48497728ea37dc6"
SRC_URI[sha256sum] = "91dc842785417208d57e5e8dc8bb40f57316c45da24a50c53b49cb8d045519d6"

RDEPENDS_${PN} = "python-py"

inherit pypi
