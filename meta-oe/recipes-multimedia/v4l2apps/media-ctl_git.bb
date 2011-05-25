DESCRIPTION = "Media controller control application"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING.GPL;md5=751419260aa954499f7abaabaa882bbe"

SRC_URI = "git://git.ideasonboard.org/media-ctl.git;protocol=git"
SRCREV = "a183835abdefb8e40650fc9eb22e2d291aac9883"

PV = "0.0.1"
S = "${WORKDIR}/git"

inherit autotools

# It needs some kernel definitions for v4l2, so it isn't machine specific
EXTRA_OECONF = "--with-kernel-headers=${STAGING_KERNEL_DIR}"


