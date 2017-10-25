DESCRIPTION = "Google gRPC"
HOMEPAGE = "http://www.grpc.io/"
SECTION = "devel/python"

DEPENDS = "python-protobuf"

SRC_URI += "file://0001-setup.py-Do-not-mix-C-and-C-compiler-options.patch \
           "
RDEPENDS_${PN} = "python-enum34 \
                  python-futures \
                  python-protobuf \
                  python-setuptools \
                  python-six \
"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-3-Clause;md5=550794465ba0ec5312d6919e203a55f9"

inherit pypi setuptools

SRC_URI[md5sum] = "6637c4dfee8166f69be7571591b6c57c"
SRC_URI[sha256sum] = "e9662782c58bc21be26163b78136eaed091dfd45fac699cb711ee9eeeb7e2f9b"

# For usage in other recipes when compiling protobuf files (e.g. by grpcio-tools)
BBCLASSEXTEND = "native"
