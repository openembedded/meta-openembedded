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

SRC_URI[md5sum] = "b564632793132fd35cdff984d6b309b0"
SRC_URI[sha256sum] = "6662af1f07cf7a3d171a7f56afc2cfeff262a5f2c52d15ae5ea581d0c367aea8"

# For usage in other recipes when compiling protobuf files (e.g. by grpcio-tools)
BBCLASSEXTEND = "native"
