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

SRC_URI[md5sum] = "f08ad971573a1d83d2d4adab13f59448"
SRC_URI[sha256sum] = "2fbe8a4af6bafb3f53d64b8c05c073bfff6c8bc829b83563f5774b0052fc656e"
