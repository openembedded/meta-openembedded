DESCRIPTION = "Yet Another V4L2 Test Application"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING.GPL;md5=751419260aa954499f7abaabaa882bbe"

SRC_URI = "git://git.ideasonboard.org/yavta.git;protocol=git"
SRCREV = "d62cb9d7435525660fd5c97941c7cf57921370b3"

PV = "0.0"
S = "${WORKDIR}/git"

do_install() {
	install -d ${D}${bindir}
	install -m 0755 yavta ${D}${bindir}
}


