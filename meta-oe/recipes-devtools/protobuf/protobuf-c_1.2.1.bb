SUMMARY = "Protocol Buffers - structured data serialisation mechanism"
DESCRIPTION = "This is protobuf-c, a C implementation of the Google Protocol Buffers data \
serialization format. It includes libprotobuf-c, a pure C library that \
implements protobuf encoding and decoding, and protoc-c, a code generator that \
converts Protocol Buffer .proto files to C descriptor code, based on the \
original protoc. protobuf-c formerly included an RPC implementation; that code \
has been split out into the protobuf-c-rpc project."
HOMEPAGE = "https://github.com/protobuf-c/protobuf-c"
SECTION = "console/tools"
LICENSE = "BSD-2-Clause"

DEPENDS = "protobuf-native protobuf"

PACKAGE_BEFORE_PN = "${PN}-compiler"
RDEPENDS_${PN}-compiler = "protobuf-compiler"
RDEPENDS_${PN}-dev += "${PN}-compiler"

LIC_FILES_CHKSUM = "file://LICENSE;md5=235c3195a3968524dc1524b4ebea0c0e"

PV .= "+git${SRCPV}"
SRCREV = "006d69bd8481738323c58af5cd4c8477d09eb9f5"

SRC_URI = "git://github.com/protobuf-c/protobuf-c.git"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

FILES_${PN}-compiler = "${bindir}"

BBCLASSEXTEND = "native nativesdk"
