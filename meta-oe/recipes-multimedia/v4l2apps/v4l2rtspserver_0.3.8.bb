SUMMARY = "V4L2rtsp streaming server"
LICENSE = "Unlicense"
LIC_FILES_CHKSUM = "file://UNLICENSE;md5=911690f51af322440237a253d695d19f"

SRC_URI = "gitsm://github.com/mpromonet/v4l2rtspserver.git;branch=master;protocol=https"
SRCREV = "b12cf350dc573ab0ff9d1b51a21daf3c218b614e"

PV .= "+0.3.8+git${SRCPV}"
S = "${WORKDIR}/git"

inherit pkgconfig cmake

DEPENDS += "live555 log4cpp"
