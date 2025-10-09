DESCRIPTION = "Google gRPC reflection"
HOMEPAGE = "https://www.grpc.io/"
SECTION = "devel/python"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://PKG-INFO;beginline=8;endline=8;md5=7145f7cdd263359b62d342a02f005515"

inherit pypi setuptools3

DEPENDS += "python3-grpcio"
PYPI_PACKAGE = "grpcio_reflection"
UPSTREAM_CHECK_PYPI_PACKAGE = "${PYPI_PACKAGE}"

SRC_URI[sha256sum] = "2be3f20b7b93e6e691a0bc761fd7e9996a940b4c96c68f6ca4f7fbc47c3f4b64"

RDEPENDS:${PN} = "python3-grpcio"

BBCLASSEXTEND = "native nativesdk"
