SUMMARY = "Simple powerful teting with python"
HOMEPAGE = "http://pytest.org"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c39b24965f4aef64222cb35de9d47cc4"

SRC_URI[md5sum] = "6dbe9bb093883f75394a689a1426ac6f"
SRC_URI[sha256sum] = "6d5bd4f7113b444c55a3bbb5c738a3dd80d43563d063fc42dcb0aaefbdd78b81"

SRC_URI_append = " file://0001-setup.py-remove-the-setup_requires-for-setuptools-scm.patch \
                   file://pytest_version_fix.patch "

RDEPENDS_${PN}_class-target += " \
    ${PYTHON_PN}-argparse \
    ${PYTHON_PN}-debugger \
    ${PYTHON_PN}-json \
    ${PYTHON_PN}-py \
    ${PYTHON_PN}-setuptools \
"

FILESEXTRAPATHS_prepend := "${THISDIR}/python-pytest:"

BBCLASSEXTEND = "native nativesdk"
