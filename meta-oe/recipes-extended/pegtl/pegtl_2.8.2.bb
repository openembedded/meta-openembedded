DESCRIPTION="header-only library for creating parsers according to Parsing Expression Grammar"
HOMEPAGE="https://github.com/taocpp/PEGTL"
LICENSE="MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=6a5195f720a8d436a4148e0cb8660400"

SRCREV = "ede3ce17aef7da27648b195dc77814cb1d3c313d"
SRC_URI = "git://github.com/taocpp/PEGTL.git;protocol=https;branch=2.x \
          "

inherit cmake

S = "${WORKDIR}/git"
