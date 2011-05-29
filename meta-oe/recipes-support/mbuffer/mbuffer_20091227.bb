DESCRIPTION = "mbuffer is a tool for buffering data streams with a large set of unique features."
HOMEPAGE = "http://www.maier-komor.de/mbuffer.html"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d32239bcb673463ab874e80d47fae504"
SECTION = "console/network"
PR = "r1"

SRC_URI = "http://www.maier-komor.de/software/mbuffer/mbuffer-20091227.tgz"
SRC_URI[md5sum] = "191663396b4b1752d7104803631b9e54"
SRC_URI[sha256sum] = "e708b66f6cec9ba090877e8eb38e5627ac69aea9ebd9bca1360a29c7398a88ce"

inherit autotools

do_configure() {
	gnu-configize
	oe_runconf
}

