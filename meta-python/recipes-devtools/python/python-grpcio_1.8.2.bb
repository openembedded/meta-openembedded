DESCRIPTION = "Google gRPC"
HOMEPAGE = "http://www.grpc.io/"
SECTION = "devel/python"

DEPENDS = "python-protobuf"

SRC_URI_append_class-target = " file://0001-setup.py-Do-not-mix-C-and-C-compiler-options.patch "

RDEPENDS_${PN} = "python-enum34 \
                  python-futures \
                  python-protobuf \
                  python-setuptools \
                  python-six \
"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-3-Clause;md5=550794465ba0ec5312d6919e203a55f9"

inherit pypi setuptools

SRC_URI[md5sum] = "4d4ce91b5a56d724227aa116f971402c"
SRC_URI[sha256sum] = "1ea1336f0d1158c4e00e96a94df84b75f6bbff9816abb6cc68cbdc9442a9ac55"

# For usage in other recipes when compiling protobuf files (e.g. by grpcio-tools)
BBCLASSEXTEND = "native"
