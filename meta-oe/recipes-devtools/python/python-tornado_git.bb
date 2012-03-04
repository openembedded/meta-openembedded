DESCRIPTION = "Tornado is an open source version of the scalable, non-blocking web server and tools that power FriendFeed."
LICENSE = "Apache"
LIC_FILES_CHKSUM = "file://README;md5=e7fb9954003d7cd93322ccf282210d1c"

PV = "2.2"
SRCREV = "02bc76155de5bf4dca243e4d0c019c0ac4c8b3be"
SRC_URI = "git://github.com/facebook/tornado.git;branch=branch2.2 \
           file://0001-disable-AI_ADDRCONFIG-flag.patch \
          "

S = "${WORKDIR}/git"

inherit setuptools


