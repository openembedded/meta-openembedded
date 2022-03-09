SUMMARY = "A python library for handling exceptions"
DESCRIPTION = "The python-meh package is a python library for handling, saving, and reporting \
exceptions."
HOMEPAGE = "https://github.com/rhinstaller/python-meh"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

inherit setuptools3_legacy

S = "${WORKDIR}/git"

SRC_URI = "git://github.com/rhinstaller/python-meh.git;protocol=https;branch=rhel9-branch \
           file://0001-setup.py-switch-from-distutils-to-setuptools.patch \
          "
SRCREV = "c321ce22950aff76611a3c6beffa02b5ea3adbed"

FILES:${PN} += "${datadir}/python-meh"
