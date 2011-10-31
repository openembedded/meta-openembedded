DESCRIPTION = "Tornado is an open source version of the scalable, non-blocking web server and tools that power FriendFeed."
LICENSE = "Apache"
LIC_FILES_CHKSUM = "file://README;md5=e7fb9954003d7cd93322ccf282210d1c"

PV = "2.1.1"
PR = "r1"
SRCREV = "970b43bddf276d7ce0022252709fcac1ff1f8c64"
SRC_URI = "git://github.com/facebook/tornado.git"

S = "${WORKDIR}/git"

inherit setuptools


