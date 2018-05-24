DESCRIPTION = "A high performance, open source, general-purpose RPC framework. \
Provides gRPC libraries for multiple languages written on top of shared C core library \
(C++, Node.js, Python, Ruby, Objective-C, PHP, C#)"
HOMEPAGE = "https://github.com/grpc/grpc"
SECTION = "libs"
LICENSE = "Apache-2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

DEPENDS = "gflags c-ares protobuf protobuf-native protobuf-c protobuf-c-native openssl"
DEPENDS_append_class-target = " gtest grpc-native "

S = "${WORKDIR}/git"
SRCREV = "db68cb3652cc7697647e9934b5316d98a6ba04d1"
BRANCH = "v1.8.x"
SRC_URI = "git://github.com/grpc/grpc.git;protocol=https;branch=${BRANCH} \
           file://0001-CMakeLists.txt-Fix-libraries-installation-for-Linux.patch \
           file://0004-CMakeLists.txt-Find-c-ares-in-target-sysroot-alone.patch \
           "
SRC_URI_append_class-target = " file://0001-CMakeLists.txt-Fix-grpc_cpp_plugin-path-during-cross.patch"

inherit cmake

EXTRA_OECMAKE = " \
    -DgRPC_CARES_PROVIDER=package \
    -DgRPC_ZLIB_PROVIDER=package \
    -DgRPC_SSL_PROVIDER=package \
    -DgRPC_PROTOBUF_PROVIDER=package \
    -DgRPC_GFLAGS_PROVIDER=package \
    -DgRPC_INSTALL=1 \
    -DBUILD_SHARED_LIBS=ON \
    "

BBCLASSEXTEND = "native"
