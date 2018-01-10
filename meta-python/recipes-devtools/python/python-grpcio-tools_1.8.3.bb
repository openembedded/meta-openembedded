DESCRIPTION = "Google gRPC tools"
HOMEPAGE = "http://www.grpc.io/"
SECTION = "devel/python"

DEPENDS = "python-grpcio"
RDEPENDS_${PN} = "python-grpcio"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-3-Clause;md5=550794465ba0ec5312d6919e203a55f9"

inherit pypi setuptools

SRC_URI[md5sum] = "108e5d231074d5eeac9dd5515e0c434f"
SRC_URI[sha256sum] = "195e5b8d4cfac2492769eb6b0f7b53fda54344a1dc7b4bbfa653889a83038219"

# For usage in other recipies when compiling protobuf files (e.g. by grpcio-tools)
BBCLASSEXTEND = "native"
