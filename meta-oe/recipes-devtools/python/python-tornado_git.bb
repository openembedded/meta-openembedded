DESCRIPTION = "Tornado is an open source version of the scalable, non-blocking web server and tools that power FriendFeed."
LICENSE = "Apache"
LIC_FILES_CHKSUM = "file://README;md5=e7fb9954003d7cd93322ccf282210d1c"

PV = "2.1.1"
PR = "r2"
SRCREV = "066a0c38fb819419b8422980b61a0fd2f6ee4739"
SRC_URI = "git://github.com/facebook/tornado.git"

S = "${WORKDIR}/git"

inherit setuptools


