SUMMARY = "Protocol Buffers - structured data serialisation mechanism"
DESCRIPTION = "Protocol Buffers are a way of encoding structured data in an \
efficient yet extensible format. Google uses Protocol Buffers for almost \
all of its internal RPC protocols and file formats."
HOMEPAGE = "http://code.google.com/p/protobuf/"
SECTION = "console/tools"
LICENSE = "BSD-3-Clause"

DEPENDS = "zlib"

LIC_FILES_CHKSUM = "file://COPYING.txt;md5=af6809583bfde9a31595a58bb4a24514"

SRC_URI = "http://protobuf.googlecode.com/files/protobuf-${PV}.tar.gz"

SRC_URI[md5sum] = "dc84e9912ea768baa1976cb7bbcea7b5"
SRC_URI[sha256sum] = "eac6969b617f397247e805267da2b0db3ff9e5a9163b123503a192fbb5776567"

EXTRA_OECONF += " --with-protoc=echo"

inherit autotools

BBCLASSEXTEND = "native nativesdk"
