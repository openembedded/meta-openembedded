DESCRIPTION = "Protocol Buffers with small code size"
LICENSE = "Zlib"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=9db4b73a55a3994384112efcdb37c01f"

SRC_URI = "git://github.com/nanopb/nanopb.git;branch=maintenance_0.4;protocol=https;tag=${PV}"
SRCREV = "93550bb52a6216a7396d0a168a42b3a88223d811"


inherit cmake

BBCLASSEXTEND = "native nativesdk"

