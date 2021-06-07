SUMMARY = "A python package that provides useful locks."
HOMEPAGE = "https://github.com/harlowja/fasteners"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4476c4be31402271e101d9a4a3430d52"

SRC_URI[sha256sum] = "b6f488d0fc9a3591a32d779400a00c3ebfadd41f45eb024d66f15a6c4e1c4a72"

inherit pypi setuptools3

RDEPENDS_${PN} += "\
    ${PYTHON_PN}-logging \
    ${PYTHON_PN}-fcntl \
"
