SUMMARY = "iftop does for network usage what top(1) does for CPU usage"
HOMEPAGE = "http://www.ex-parrot.com/pdw/iftop/"

DEPENDS = "libpcap ncurses"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

SRC_URI = "http://www.ex-parrot.com/pdw/iftop/download/iftop-${PV}.tar.gz"
SRC_URI[md5sum] = "fef521a49ec0122458d02c64212af3c5"
SRC_URI[sha256sum] = "1b193037bb3019afae88cd94c87468aac4b045d81ab816ed8d2f489716b14027"

inherit autotools-brokensep

