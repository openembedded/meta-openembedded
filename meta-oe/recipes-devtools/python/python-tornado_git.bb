DESCRIPTION = "Tornado is an open source version of the scalable, non-blocking web server and tools that power FriendFeed."
LICENSE = "Apache"
LIC_FILES_CHKSUM = "file://README;md5=e7fb9954003d7cd93322ccf282210d1c"

PV = "2.1.1"
PR = "r4"
SRCREV = "112fdb48b06b754d1f94335ea9e2fd62a07a5fe3"
SRC_URI = "git://github.com/facebook/tornado.git \
           file://0001-disable-AI_ADDRCONFIG-flag.patch \
          "

S = "${WORKDIR}/git"

inherit setuptools


