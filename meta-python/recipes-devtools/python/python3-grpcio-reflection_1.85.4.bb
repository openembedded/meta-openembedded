DESCRIPTION = "Google gRPC reflection"
HOMEPAGE = "https://www.grpc.io/"
SECTION = "devel/python"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=fe8c552b7748e45c00ac55ac0257f6c9"

inherit pypi python_setuptools_build_meta

SRC_URI[sha256sum] = "f4704dc50f5d971ba27a11b35c79b0ee9d5f4ff6ced9f232502dcbbcdf4e7298"

DEPENDS += "python3-grpcio python3-wheel-native"

RDEPENDS:${PN} = "python3-grpcio"

BBCLASSEXTEND = "native nativesdk"
