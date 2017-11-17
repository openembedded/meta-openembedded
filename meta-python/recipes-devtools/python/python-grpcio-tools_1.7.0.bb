DESCRIPTION = "Google gRPC tools"
HOMEPAGE = "http://www.grpc.io/"
SECTION = "devel/python"

DEPENDS = "python-grpcio"
RDEPENDS_${PN} = "python-grpcio"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-3-Clause;md5=550794465ba0ec5312d6919e203a55f9"

inherit pypi setuptools

SRC_URI[md5sum] = "b2a25251b829aae9865aa77970fc1703"
SRC_URI[sha256sum] = "217b1a6f7ef10b79d4c98585a10a81027311cb5ef1c95d82880e819da4655e52"

# For usage in other recipies when compiling protobuf files (e.g. by grpcio-tools)
BBCLASSEXTEND = "native"
