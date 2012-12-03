DESCRIPTION = "Eject allows removable media (typically a CD-ROM, floppy disk, tape, or JAZ or ZIP disk) to be ejected under software control."
HOMEPAGE = "http://eject.sourceforge.net/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=8ca43cbc842c2336e835926c2166c28b"

inherit autotools gettext update-alternatives

SRC_URI = "http://sources.openembedded.org/${P}.tar.gz"

SRC_URI[md5sum] = "b96a6d4263122f1711db12701d79f738"
SRC_URI[sha256sum] = "ef9f7906484cfde4ba223b2682a37058f9a3c7d3bb1adda7a34a67402e2ffe55"

S = "${WORKDIR}/${PN}"

PR = "r1"

ALTERNATIVE_${PN} = "volname eject"
ALTERNATIVE_LINK_NAME[volname] = "${bindir}/volname"
ALTERNATIVE_LINK_NAME[eject] = "${bindir}/eject"
ALTERNATIVE_PRIORITY[volname] = "100"
ALTERNATIVE_PRIORITY[eject] = "100"

