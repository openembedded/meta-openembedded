SUMMARY = "A Modbus library"
DESCRIPTION = "libmodbus is a C library designed to provide a fast and robust \
implementation of the Modbus protocol. It runs on Linux, Mac OS X, FreeBSD, \
QNX and Windows."
HOMEPAGE = "http://www.libmodbus.org/"
SECTION = "libs"

LICENSE = "LGPLv2.1+"
LIC_FILES_CHKSUM = "file://COPYING.LESSER;md5=4fbd65380cdd255951079008b364516c"

SRC_URI = "http://github.com/downloads/stephane/${BPN}/${BP}.tar.gz"

inherit autotools pkgconfig

SRC_URI[md5sum] = "b5042d833c6c132e6fb2551af133a342"
SRC_URI[sha256sum] = "6fc7cf91f7293e522afde6ae5fc605c6cd7bdca4ee2ec953db5bb91158ab8677"
