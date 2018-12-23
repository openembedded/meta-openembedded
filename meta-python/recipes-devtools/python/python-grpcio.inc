DESCRIPTION = "Google gRPC"
HOMEPAGE = "http://www.grpc.io/"
SECTION = "devel/python"

DEPENDS_append = "${PYTHON_PN}-protobuf"

FILESEXTRAPATHS_prepend := "${THISDIR}/python-grpcio:"

SRC_URI_append_class-target = " file://0001-setup.py-Do-not-mix-C-and-C-compiler-options.patch \
                                file://ppc-boringssl-support.patch \
"

RDEPENDS_${PN} = "${PYTHON_PN}-protobuf \
                  ${PYTHON_PN}-setuptools \
                  ${PYTHON_PN}-six \
"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-3-Clause;md5=550794465ba0ec5312d6919e203a55f9"

inherit pypi
SRC_URI[md5sum] = "dd46d7270dfce713c03f45fae999cae3"
SRC_URI[sha256sum] = "fd6774bbb6c717f725b39394757445ead4f69c471118364933aadb81a4f16961"

CLEANBROKEN = "1"

BBCLASSEXTEND = "native nativesdk"
