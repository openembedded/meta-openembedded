SUMMARY = "Python interface for Remember The Milk API"
AUTHOR = "Sridhar Ratnakumar / srid"
HOMEPAGE = "http://pypi.python.org/pypi/pyrtm"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://PKG-INFO;beginline=8;endline=8;md5=a53cbc7cb75660694e138ba973c148df"
DEPENDS = "python-native"

inherit distutils

SRC_URI = "http://pypi.python.org/packages/source/p/${BPN}/${BP}.tar.gz"
SRC_URI[md5sum] = "34423f39caf7df8a9db8e243be9cf1a0"
SRC_URI[sha256sum] = "d9d46d096a38cb692b55f4ee9f364d54348298e9497b11c1ef057539b5de1567"
