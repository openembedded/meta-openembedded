SUMMARY = "A python library for handling exceptions"
DESCRIPTION = "The python-meh package is a python library for handling, saving, and reporting \
exceptions."
HOMEPAGE = "http://git.fedorahosted.org/git/?p=python-meh.git"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

inherit setuptools3

S = "${WORKDIR}/git"

SRC_URI = "git://github.com/rhinstaller/python-meh.git;protocol=https;branch=rhel9-branch \
           file://0001-setup.py-switch-from-distutils-to-setuptools.patch \
          "
SRCREV = "c321ce22950aff76611a3c6beffa02b5ea3adbed"

PIP_INSTALL_PACKAGE = "python_meh"

do_install:append () {
    install -d ${D}/${datadir}/python-meh
    mv ${D}${PYTHON_SITEPACKAGES_DIR}${datadir}/python-meh/* ${D}/${datadir}/python-meh/
}

FILES:${PN} += "${datadir}/*"

