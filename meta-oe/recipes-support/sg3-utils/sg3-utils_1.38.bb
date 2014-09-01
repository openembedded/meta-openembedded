SUMMARY = "Utilities for working with generic SCSI devices"

DESCRIPTION = "This package contains low level utilities for devices that use the SCSI command set"

HOMEPAGE = "http://sg.danny.cz/sg/sg3_utils.html"
SECTION = "console/admin"

LICENSE = "GPLv2+ & BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=f90da7fc52172599dbf082d7620f18ca"

SRC_URI = "http://sg.danny.cz/sg/p/sg3_utils-${PV}.tgz"
SRC_URI[md5sum] = "e532a3d6648c422347f6d84cac469263"
SRC_URI[sha256sum] = "d7d3a8b4b698e2c17c8f16bb876946720260d4d659d471c5d7c12ce652ba38f1"

inherit autotools-brokensep

S = "${WORKDIR}/sg3_utils-${PV}"

RDEPENDS_${PN} += "bash"
