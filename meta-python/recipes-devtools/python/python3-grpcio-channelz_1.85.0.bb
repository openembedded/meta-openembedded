DESCRIPTION = "Google gRPC channelz"
HOMEPAGE = "https://www.grpc.io/"
SECTION = "devel/python"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=fe8c552b7748e45c00ac55ac0257f6c9"

inherit pypi python_setuptools_build_meta

SRC_URI[sha256sum] = "2a16ad645cf4897325c617c6eb2a9f82ffaf28978c3de3b1039ef06e3bef8c42"

DEPENDS += "python3-grpcio python3-wheel-native"

RDEPENDS:${PN} = "python3-grpcio"

BBCLASSEXTEND = "native nativesdk"
