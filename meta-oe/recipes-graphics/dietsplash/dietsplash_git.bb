DESCRIPTION = "Simple bootsplash for systemd systems"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"

# Really, no depends besides a C library

PV = "0.3"
PR = "r1"

SRCREV = "3098e4ad8aa07ad90b2a4413c0abeb6dcd8f1233"
SRC_URI = "git://github.com/lucasdemarchi/dietsplash.git;protocol=git"

inherit autotools

S = "${WORKDIR}/git"

EXTRA_OECONF = " --with-systemdsystemunitdir=${systemd_unitdir}/system \
                 --disable-staticimages"

FILES_${PN} += "${systemd_unitdir}/system/"
