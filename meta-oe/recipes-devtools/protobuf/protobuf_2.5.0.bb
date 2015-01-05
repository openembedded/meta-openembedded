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

SRC_URI[md5sum] = "b751f772bdeb2812a2a8e7202bf1dae8"
SRC_URI[sha256sum] = "c55aa3dc538e6fd5eaf732f4eb6b98bdcb7cedb5b91d3b5bdcf29c98c293f58e"

EXTRA_OECONF += " --with-protoc=echo"

inherit autotools

BBCLASSEXTEND = "native nativesdk"
