DESCRIPTION = "Google gRPC tools"
HOMEPAGE = "http://www.grpc.io/"
SECTION = "devel/python"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://PKG-INFO;beginline=8;endline=8;md5=7145f7cdd263359b62d342a02f005515"

inherit pypi setuptools3

PYPI_PACKAGE = "grpcio_tools"
UPSTREAM_CHECK_PYPI_PACKAGE = "${PYPI_PACKAGE}"

DEPENDS += "python3-grpcio"

SRC_URI += "file://0001-setup.py-Do-not-mix-C-and-C-compiler-options.patch \
            file://0001-protobuf-Disable-musttail-attribute-on-mips.patch \
            "
SRC_URI[sha256sum] = "3e1a98f4d9decb84979e1ddd3deb09c0a33a84b6e3c0776d5bde4097e3ab66dd"

RDEPENDS:${PN} = "python3-grpcio"

BBCLASSEXTEND = "native nativesdk"
