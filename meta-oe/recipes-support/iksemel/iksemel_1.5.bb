SUMMARY = "Fast and portable XML parser and Jabber protocol library"
AUTHOR = "Gurer Ozen <meduketto at gmail.com>"
HOMEPAGE = "http://iksemel.googlecode.com"
SECTION = "libs"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=d8045f3b8f929c1cb29a1e3fd737b499"
DEPENDS = "gnutls"
RDEPENDS_${PN} = "libcrypto libssl"

SRCREV = "${AUTOREV}"
SRC_URI = "git://github.com/meduketto/iksemel.git;protocol=https;branch=master"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

EXTRA_OECONF += "--disable-python"
