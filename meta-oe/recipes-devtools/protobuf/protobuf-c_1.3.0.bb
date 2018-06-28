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
LIC_FILES_CHKSUM = "file://LICENSE;md5=cb901168715f4782a2b06c3ddaefa558"

DEPENDS = "protobuf-native protobuf"

PV .= "+git${SRCPV}"
SRCREV = "dac1a65feac4ad72f612aab99f487056fbcf5c1a"

SRC_URI = "git://github.com/protobuf-c/protobuf-c.git"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

PACKAGE_BEFORE_PN = "${PN}-compiler"

FILES_${PN}-compiler = "${bindir}"

RDEPENDS_${PN}-compiler = "protobuf-compiler"
RDEPENDS_${PN}-dev += "${PN}-compiler"

BBCLASSEXTEND = "native nativesdk"
