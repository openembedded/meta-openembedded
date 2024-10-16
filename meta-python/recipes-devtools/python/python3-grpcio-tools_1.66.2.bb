DESCRIPTION = "Google gRPC tools"
HOMEPAGE = "http://www.grpc.io/"
SECTION = "devel/python"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://PKG-INFO;beginline=8;endline=8;md5=7145f7cdd263359b62d342a02f005515"

inherit pypi setuptools3

PYPI_PACKAGE = "grpcio_tools"

DEPENDS += "python3-grpcio"

SRC_URI += "file://0001-setup.py-Do-not-mix-C-and-C-compiler-options.patch \
            file://0001-protobuf-Disable-musttail-attribute-on-mips.patch \
            "
SRC_URI[sha256sum] = "4a36e07913d26ba5ccfd2685ba63ca97f26b08c249d2cc9e74dda37efa49d7e4"

RDEPENDS:${PN} = "python3-grpcio"

BBCLASSEXTEND = "native nativesdk"
