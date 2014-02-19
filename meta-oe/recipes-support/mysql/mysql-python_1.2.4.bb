SUMMARY = "Python interface to MySQL"
HOMEPAGE = "https://github.com/farcepest/MySQLdb1"
SECTION = "devel/python"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://GPL-2.0;md5=b234ee4d69f5fce4486a80fdaf4a4263"

DEPENDS = "mysql5"

SRCNAME = "MySQL-python"

SRC_URI = "https://pypi.python.org/packages/source/M/${SRCNAME}/${SRCNAME}-${PV}.zip \
           file://remove-distribute.patch"

SRC_URI[md5sum] = "ddf2386daf10a97af115ffad2ed4a9a0"
SRC_URI[sha256sum] = "e405f9d6be33923d428acaa4db4f4470427f1d15ea0d2d82a933449ace26bbd9"

S = "${WORKDIR}/${SRCNAME}-${PV}"

inherit setuptools
