DESCRIPTION = "Google gRPC tools"
HOMEPAGE = "http://www.grpc.io/"
SECTION = "devel/python"

DEPENDS = "python-grpcio"
RDEPENDS_${PN} = "python-grpcio"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-3-Clause;md5=550794465ba0ec5312d6919e203a55f9"

inherit pypi setuptools

SRC_URI[md5sum] = "357ea84410d1888e1978a857c0289ff5"
SRC_URI[sha256sum] = "71d9f640aac2db77787869c863fe80cec1f259a29caa89a8fc21c6b1d07766fd"

# For usage in other recipies when compiling protobuf files (e.g. by grpcio-tools)
BBCLASSEXTEND = "native"
