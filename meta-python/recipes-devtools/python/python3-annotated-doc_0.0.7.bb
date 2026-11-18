SUMMARY = "Document parameters, class attributes, return types, and variables inline, with Annotated."
HOMEPAGE = "https://github.com/fastapi/annotated-doc"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e36e91f278975b8bb76a769f32582892"

SRC_URI[sha256sum] = "0a30ef8f5322a4173720ae6712dc9a5e58f5a19868f3f167b4b5778db066c2b5"

inherit pypi python_pdm

RDEPENDS:${PN} += "python3-compression"
