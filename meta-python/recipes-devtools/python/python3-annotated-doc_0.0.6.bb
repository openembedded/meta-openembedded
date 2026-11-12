SUMMARY = "Document parameters, class attributes, return types, and variables inline, with Annotated."
HOMEPAGE = "https://github.com/fastapi/annotated-doc"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e36e91f278975b8bb76a769f32582892"

SRC_URI[sha256sum] = "7969bb69912085b2285849c0dcac0b4b374fd180c13cf10a87f284ffcf347bbf"

inherit pypi python_pdm

RDEPENDS:${PN} += "python3-compression"
