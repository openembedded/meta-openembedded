DESCRIPTION = "Utilities for working with generic SCSI devices"
HOMEPAGE = "http://sg.danny.cz/sg/sg3_utils.html"
SECTION = "console/admin"

LICENSE = "GPLv2+ & BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=1cc481c050dc92e12db9c8145043d5dc"

PR = "r1"

SRC_URI = "http://sg.danny.cz/sg/p/sg3_utils-${PV}.tgz"
RC_URI[md5sum] = "9431e1944cde829a24dedf027e1364b5"
SRC_URI[sha256sum] = "d84b38b61f0ca3941eb1c48a5858f93d1d1b70a623e579f5ecce2440e18410a4"

inherit autotools

S = "${WORKDIR}/sg3_utils-${PV}"
