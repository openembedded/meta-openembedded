DESCRIPTION = "iCal and scheduling (RFC 2445, 2446, 2447) library"
HOMEPAGE = "http://sourceforge.net/projects/freeassociation/"
BUGTRACKER = "http://sourceforge.net/tracker/?group_id=16077&atid=116077"
LICENSE = "LGPLv2.1 | MPL-1"
LIC_FILES_CHKSUM = "file://COPYING;md5=d4fc58309d8ed46587ac63bb449d82f8 \
                    file://LICENSE;md5=d1a0891cd3e582b3e2ec8fe63badbbb6"
SECTION = "libs"

PR = "r0"

SRC_URI = "${SOURCEFORGE_MIRROR}/project/freeassociation/${BPN}/${P}/${BPN}-${PV}.tar.gz\
           file://pthread-fix.patch"

SRC_URI[md5sum] = "e549f434d5fbf9cd156c60ed4943618f"
SRC_URI[sha256sum] = "2ae78b0757f0dd13431acf42a9a8d038339fd4767fd5134e650bf60ee0b4dff0"

inherit autotools
