SUMMARY = "Library to enable your code run as a daemon process on Unix-like systems"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=13e982bf1b7b164b9d6d1665dac83873"
SRCNAME = "daemonize"

inherit pypi

RDEPENDS_${PN} = "\
               python-fcntl \
               python-unixadmin \
               python-logging \
               python-resource \
"

SRC_URI[md5sum] = "f0d401a0a501ba9d36e516ea89146eb2"
SRC_URI[sha256sum] = "6fa43b56c293ff8bb180008fe47ed49f7d006cdeb0eaa84f157249800d3194d4"
