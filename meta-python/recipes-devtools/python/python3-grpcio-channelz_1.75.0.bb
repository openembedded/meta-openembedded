DESCRIPTION = "Google gRPC channelz"
HOMEPAGE = "https://www.grpc.io/"
SECTION = "devel/python"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://PKG-INFO;beginline=8;endline=8;md5=7145f7cdd263359b62d342a02f005515"

inherit pypi setuptools3

DEPENDS += "python3-grpcio"
PYPI_PACKAGE = "grpcio_channelz"
UPSTREAM_CHECK_PYPI_PACKAGE = "${PYPI_PACKAGE}"

SRC_URI[sha256sum] = "d521973a343806a8384bf87ceae93cf2e9f10f8fab02c12be7a2e3d5d0e4e68f"

RDEPENDS:${PN} = "python3-grpcio"

BBCLASSEXTEND = "native nativesdk"
