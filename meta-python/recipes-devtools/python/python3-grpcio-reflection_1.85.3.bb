DESCRIPTION = "Google gRPC reflection"
HOMEPAGE = "https://www.grpc.io/"
SECTION = "devel/python"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=fe8c552b7748e45c00ac55ac0257f6c9"

inherit pypi python_setuptools_build_meta

SRC_URI[sha256sum] = "12433efd1fb76a2c34611843763330f43c26167419209a55a0383f6a85457e5a"

DEPENDS += "python3-grpcio python3-wheel-native"

RDEPENDS:${PN} = "python3-grpcio"

BBCLASSEXTEND = "native nativesdk"
