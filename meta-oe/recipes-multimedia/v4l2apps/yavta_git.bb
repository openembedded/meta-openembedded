DESCRIPTION = "Yet Another V4L2 Test Application"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING.GPL;md5=751419260aa954499f7abaabaa882bbe"

SRC_URI = "git://git.ideasonboard.org/yavta.git;protocol=git \
           file://0001-Add-stdout-mode-to-allow-streaming-over-the-network-.patch"
SRCREV = "82ff2efdb9787737b9f21b6f4759f077c827b238"

PV = "0.0"
PR = "r2"
S = "${WORKDIR}/git"

do_install() {
	install -d ${D}${bindir}
	install -m 0755 yavta ${D}${bindir}
}


