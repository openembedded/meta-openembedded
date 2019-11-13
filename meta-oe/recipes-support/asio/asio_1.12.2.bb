DESCRIPTION = "Asio is a cross-platform C++ library for network and low-level \
        I/O programming that provides developers with a consistent asynchronous \
        model using a modern C++ approach."
AUTHOR = "Christopher M. Kohlhoff (chris at kohlhoff dot com)"
HOMEPAGE = "http://think-async.com/Asio"
SECTION = "libs"
LICENSE = "BSL-1.0"

DEPENDS = "openssl"

SRC_URI = "${SOURCEFORGE_MIRROR}/asio/${BP}.tar.bz2"

inherit autotools

ALLOW_EMPTY_${PN} = "1"

LIC_FILES_CHKSUM = "file://COPYING;md5=3e73f311a3af69e6df275e8c3b1c09b5"

SRC_URI[md5sum] = "312e53385e2daad10f08d57f91ad3431"
SRC_URI[sha256sum] = "4e27dcb37456ba707570334b91f4798721111ed67b69915685eac141895779aa"

SRC_URI = "${SOURCEFORGE_MIRROR}/asio/${BP}.tar.bz2"

PACKAGECONFIG ??= "boost"

PACKAGECONFIG[boost] = "--with-boost,--without-boost,boost"
