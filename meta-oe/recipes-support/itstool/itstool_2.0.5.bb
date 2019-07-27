SUMMARY = "ITS Tool allows you to translate your XML documents with PO files"
HOMEPAGE = "http://itstool.org/"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=59c57b95fd7d0e9e238ebbc7ad47c5a5"

inherit autotools python3native

DEPENDS = "libxml2-native"

SRC_URI = "http://files.itstool.org/${BPN}/${BPN}-${PV}.tar.bz2 \
           file://0001-Don-t-use-build-time-hardcoded-python-binary-path.patch"
SRC_URI[md5sum] = "655c6f78fc64faee45adcc45ccc5a57e"
SRC_URI[sha256sum] = "100506f8df62cca6225ec3e631a8237e9c04650c77495af4919ac6a100d4b308"

BBCLASSEXTEND = "native"

RDEPENDS_${PN} += "libxml2-python"
RDEPENDS_${PN}_class-native = ""
