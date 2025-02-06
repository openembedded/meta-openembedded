DESCRIPTION = "Google gRPC"
HOMEPAGE = "http://www.grpc.io/"
SECTION = "devel/python"
LICENSE = "Apache-2.0 & BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=731e401b36f8077ae0c134b59be5c906"

DEPENDS += "c-ares openssl python3-protobuf re2 zlib"

SRC_URI += "file://0001-python-enable-unbundled-cross-compilation.patch \
           file://abseil-ppc-fixes.patch \
           "
SRC_URI[sha256sum] = "936fa44241b5379c5afc344e1260d467bee495747eaf478de825bab2791da6f5"

RDEPENDS:${PN} = "python3-protobuf"

inherit setuptools3
inherit pypi

CFLAGS:append:libc-musl = " -D_LARGEFILE64_SOURCE"

export GRPC_PYTHON_BUILD_SYSTEM_CARES = "1"
export GRPC_PYTHON_BUILD_SYSTEM_OPENSSL = "1"
export GRPC_PYTHON_BUILD_SYSTEM_RE2 = "1"
export GRPC_PYTHON_BUILD_SYSTEM_ZLIB = "1"

GRPC_CFLAGS ?= ""
GRPC_CFLAGS:append:toolchain-clang = " -fvisibility=hidden -fno-wrapv -fno-exceptions"
export GRPC_PYTHON_CFLAGS = "${GRPC_CFLAGS}"

CLEANBROKEN = "1"

BBCLASSEXTEND = "native nativesdk"

CCACHE_DISABLE = "1"
