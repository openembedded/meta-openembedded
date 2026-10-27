DESCRIPTION = "Google gRPC reflection"
HOMEPAGE = "https://www.grpc.io/"
SECTION = "devel/python"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=fe8c552b7748e45c00ac55ac0257f6c9"

inherit pypi python_setuptools_build_meta

SRC_URI[sha256sum] = "b50383d750c2bbddb9c2b163b41de2219550e4393e1f1e0fb0080b8e1284fdd2"

DEPENDS += "python3-grpcio python3-wheel-native"

RDEPENDS:${PN} = "python3-grpcio"

BBCLASSEXTEND = "native nativesdk"
