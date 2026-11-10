SUMMARY = "Dynamic version generation"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=059eed55dbfd3fea022510ea62c95dc1"

SRC_URI[sha256sum] = "b1435d8d2b0aceef240cde8e8f6ba910e85b1aa0e22fa9e21454d2d22fb0a89c"

inherit pypi python_poetry_core

BBCLASSEXTEND = "native nativesdk"
