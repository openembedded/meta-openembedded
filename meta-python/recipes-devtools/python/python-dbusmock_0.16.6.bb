# Copyright (c) 2014 LG Electronics, Inc.

SUMMARY = "With this program/Python library you can easily create mock objects on D-Bus"
AUTHOR = "Martin Pitt <martin.pitt@ubuntu.com>"

LICENSE = "GPL-3.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=e6a600fd5e1d9cbde2d983680233ad02"

SRC_URI[md5sum] = "6af369964653098f2cd5e7c4e060a533"
SRC_URI[sha256sum] = "87c551030369005a6c818d9f27e931090bff837e70af060b65281e9ea77108cf"

SRC_URI += " \
    file://0001-Add-functionality-to-add-own-objects-to-internal-obj.patch \
    file://0002-Add-possibility-to-import-templates-from-packages.patch \
"

PYPI_PACKAGE = "python-dbusmock"

inherit pypi setuptools

RDEPENDS_${PN} += "\
    ${PYTHON_PN}-dbus \
    ${PYTHON_PN}-importlib \
    ${PYTHON_PN}-pygobject \
    ${PYTHON_PN}-xml \
    "
