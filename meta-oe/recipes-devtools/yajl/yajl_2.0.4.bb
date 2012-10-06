DESCRIPTION = "Yet Another JSON Library - A Portable JSON parsing and serialization library in ANSI C"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=74f541bd9a2b6c8e5d0714bcdc327f32"

SRCREV = "fee1ebef9fa7dc0e9f5a23f37123b19b68c796ff"
SRC_URI = "git://github.com/lloyd/yajl.git"

S = "${WORKDIR}/git"

inherit cmake lib_package
