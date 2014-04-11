SUMMARY = "some essentials for string handling (and a bit more)"
HOMEPAGE = "http://libestr.adiscon.com/"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=9d6c993486c18262afba4ca5bcb894d0"

SRC_URI = "http://libestr.adiscon.com/files/download/${BP}.tar.gz"

SRC_URI[md5sum] = "f48a7098080eebfe7842673c23dcd064"
SRC_URI[sha256sum] = "b9c819d30397845fdf85bb1f37c52e81de23aa3b061ec6cc4386f1b942960153"

inherit autotools
