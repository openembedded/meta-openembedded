DESCRIPTION = "Google gRPC"
HOMEPAGE = "http://www.grpc.io/"
SECTION = "devel/python"
LICENSE = "Apache-2.0 & BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=731e401b36f8077ae0c134b59be5c906"

DEPENDS += "${PYTHON_PN}-protobuf"

SRC_URI += "file://0001-setup.py-Do-not-mix-C-and-C-compiler-options.patch"
SRC_URI:append:class-target = " file://ppc-boringssl-support.patch \
                                file://mips_bigendian.patch \
                                file://0001-Include-missing-cstdint-header.patch \
                                file://abseil-ppc-fixes.patch;patchdir=third_party/abseil-cpp \
                                file://0001-direct_mmap-Use-off_t-on-linux.patch;patchdir=third_party/abseil-cpp \
                                file://0001-zlib-Include-unistd.h-for-open-close-C-APIs.patch \
"
SRC_URI[sha256sum] = "0ff789ae7d8ddd76d2ac02e7d13bfef6fc4928ac01e1dcaa182be51b6bcc0aaa"

RDEPENDS:${PN} = "${PYTHON_PN}-protobuf \
                  ${PYTHON_PN}-setuptools \
                  ${PYTHON_PN}-six \
"

inherit setuptools3
inherit pypi

CFLAGS:append:libc-musl = " -D_LARGEFILE64_SOURCE"

export GRPC_PYTHON_DISABLE_LIBC_COMPATIBILITY = "1"

BORING_SSL_PLATFORM:arm = "linux-arm"
BORING_SSL_PLATFORM:x86-64 = "linux-x86_64"
BORING_SSL_PLATFORM:aarch64 = "linux-aarch64"
BORING_SSL_PLATFORM ?= "unsupported"
export GRPC_BORING_SSL_PLATFORM = "${BORING_SSL_PLATFORM}"
export GRPC_BUILD_OVERRIDE_BORING_SSL_ASM_PLATFORM = "${BORING_SSL_PLATFORM}"

BORING_SSL:arm = "1"
BORING_SSL:x86-64 = "1"
BORING_SSL:aarch64 = "1"
BORING_SSL ?= "0"
export GRPC_BUILD_WITH_BORING_SSL_ASM = "${BORING_SSL}"

GRPC_CFLAGS ?= ""
GRPC_CFLAGS:append:toolchain-clang = " -fvisibility=hidden -fno-wrapv -fno-exceptions"
export GRPC_PYTHON_CFLAGS = "${GRPC_CFLAGS}"

CLEANBROKEN = "1"

BBCLASSEXTEND = "native nativesdk"

CCACHE_DISABLE = "1"
