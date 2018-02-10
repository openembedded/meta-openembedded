SUMMARY = "Simple powerful teting with python"
HOMEPAGE = "http://pytest.org"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c39b24965f4aef64222cb35de9d47cc4"

SRC_URI[md5sum] = "6f402b2115c2dc97bbf69739990a1d79"
SRC_URI[sha256sum] = "6074ea3b9c999bd6d0df5fa9d12dd95ccd23550df2a582f5f5b848331d2e82ca"

SRC_URI_append = " file://0001-setup.py-remove-the-setup_requires-for-setuptools-scm.patch \
                   file://pytest_version_fix.patch "

RDEPENDS_${PN}_class-target += " \
    ${PYTHON_PN}-attrs \
    ${PYTHON_PN}-debugger \
    ${PYTHON_PN}-json \
    ${PYTHON_PN}-pluggy \
    ${PYTHON_PN}-py \
    ${PYTHON_PN}-setuptools \
    ${PYTHON_PN}-six \
"

FILESEXTRAPATHS_prepend := "${THISDIR}/python-pytest:"

BBCLASSEXTEND = "native nativesdk"
