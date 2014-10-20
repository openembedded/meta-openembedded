SUMMARY = "Logging Framework for C"
HOMEPAGE = "http://log4c.sourceforge.net"
LICENSE = "LGPLv2"

LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

SRC_URI = "http://cznic.dl.sourceforge.net/project/log4c/log4c/${PV}/log4c-${PV}.tar.gz"
SRC_URI[md5sum] = "0d94919136e1d16b68427562e74cb3dd"
SRC_URI[sha256sum] = "5991020192f52cc40fa852fbf6bbf5bd5db5d5d00aa9905c67f6f0eadeed48ea"

S = "${WORKDIR}/${PN}-${PV}"

EXTRA_OECONF = "--prefix=/usr"

inherit autotools

