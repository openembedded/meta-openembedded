SUMMARY = "Memory Efficient Serialization Library - Python3 Modules"
HOMEPAGE = "https://github.com/google/flatbuffers"
SECTION = "console/tools"
LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://../LICENSE.txt;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRCREV = "8367664f15c3ea2ee58c67dacf9d630b49466de5"
SRC_URI = "git://github.com/google/flatbuffers.git;branch=master;protocol=https"
S = "${WORKDIR}/git/python"

RDEPENDS:${PN} = "flatbuffers"

inherit setuptools3

BBCLASSEXTEND = "native"
