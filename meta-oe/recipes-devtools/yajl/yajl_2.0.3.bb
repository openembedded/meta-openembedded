DESCRIPTION = "Yet Another JSON Library - A Portable JSON parsing and serialization library in ANSI C"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=74f541bd9a2b6c8e5d0714bcdc327f32"

inherit cmake

SRCREV = "04a090f32f6bda183317e9336224831b42bf13f9"
SRC_URI = "git://github.com/lloyd/yajl.git"

S = "${WORKDIR}/git"

