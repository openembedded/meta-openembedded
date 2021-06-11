SUMMARY  = "OpenCL API C++ bindings"
DESCRIPTION = "OpenCL API C++ bindings from Khronos"

SRC_URI = "git://github.com/KhronosGroup/OpenCL-CLHPP.git;protocol=https"

LICENSE  = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=3b83ef96387f14655fc854ddc3c6bd57"

PV = "2.0.14+git${SRCPV}"
SRCREV = "89d843beba559f65e4a77833fcf8604e874a3371"

S = "${WORKDIR}/git"

inherit cmake

EXTRA_OECMAKE = " \
                  -DBUILD_DOCS=OFF \
                  -DBUILD_EXAMPLES=OFF \
                  -DBUILD_TESTS=OFF \
                  "

# Headers only so PN is empty
RDEPENDS_${PN}-dev = ""
