SUMMARY = "Utilities for working with generic SCSI devices"

DESCRIPTION = "This package contains low level utilities for devices that use the SCSI command set"

HOMEPAGE = "http://sg.danny.cz/sg/sg3_utils.html"
SECTION = "console/admin"

LICENSE = "GPLv2+ & BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=f90da7fc52172599dbf082d7620f18ca"

SRC_URI = "http://sg.danny.cz/sg/p/sg3_utils-${PV}.tgz"

SRC_URI[md5sum] = "4a74ed491b0891fc60cd91d2b5d68331"
SRC_URI[sha256sum] = "44a9ecfd3af018fa9f3586067a8fa5f4874451c3d773d74436d262a7e530ee83"

inherit autotools-brokensep

S = "${WORKDIR}/sg3_utils-${PV}"

RDEPENDS_${PN} += "bash"
