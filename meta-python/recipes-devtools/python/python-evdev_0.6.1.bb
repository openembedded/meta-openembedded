SUMMARY = "Python evdev lib"
HOMEPAGE = "https://github.com/gvalkov/python-evdev"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=18debddbb3f52c661a129724a883a8e2"

SRC_URI[md5sum] = "8a3ff1012534963cfc16efebcc208fa6"
SRC_URI[sha256sum] = "67b3c71461e5ce191a34cd4ec17f6b3d59e66f6013f98f84c2fba817b8738a3b"

inherit pypi setuptools

RDEPENDS_${PN} += "\
    ${PYTHON_PN}-ctypes \
    ${PYTHON_PN}-fcntl \
    ${PYTHON_PN}-io \
    ${PYTHON_PN}-shell \
    ${PYTHON_PN}-stringold \
    "
