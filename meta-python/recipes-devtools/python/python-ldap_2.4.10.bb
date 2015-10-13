#
# Copyright (C) 2012 Wind River Systems, Inc.
#
SUMMARY = "Provides a wrapper in Python to LDAP"
DESCRIPTION = "This module provides access to the LDAP \
(Lightweight Directory Access Protocol) through Python operations \
instead of C API. The module mainly acts as a wrapper for the \
OpenLDAP 2.x libraries. Errors will appear as exceptions."

SECTION = "devel/python"
LICENSE = "PSF"
HOMEPAGE = "http://www.python-ldap.org/"
DEPENDS = "python openldap cyrus-sasl"

SRC_URI = "http://pypi.python.org/packages/source/p/${PN}/${BPN}-${PV}.tar.gz"

inherit setuptools

LIC_FILES_CHKSUM = "file://LICENCE;md5=a41c82edffa04912007cae1d20cac555"
SRC_URI[md5sum] = "a15827ca13c90e9101e5e9405c1d83be"
SRC_URI[sha256sum] = "67cc7801bf24c29386ab99966ceb68d6a60fa9e0566cc95a4fbb2c4695a8ce54"

do_configure_prepend() {
    sed -i -e 's:^library_dirs =.*::' setup.cfg
    sed -i -e 's:^include_dirs =.*:include_dirs = =/usr/include/sasl/:' setup.cfg
}
