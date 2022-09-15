SUMMARY = "V4L2rtsp streaming server"
LICENSE = "Unlicense"
LIC_FILES_CHKSUM = "file://LICENSE;md5=911690f51af322440237a253d695d19f"

SRC_URI = "gitsm://github.com/mpromonet/v4l2rtspserver.git;branch=master;protocol=https"
SRCREV = "3f81e89799bd7f1a586506aebad241b67398aca8"

S = "${WORKDIR}/git"

inherit pkgconfig cmake

DEPENDS += "live555 log4cpp"
