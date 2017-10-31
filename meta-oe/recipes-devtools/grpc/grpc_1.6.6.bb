DESCRIPTION = "A high performance, open source, general-purpose RPC framework. \
Provides gRPC libraries for multiple languages written on top of shared C core library \
(C++, Node.js, Python, Ruby, Objective-C, PHP, C#)"
HOMEPAGE = "https://github.com/grpc/grpc"
SECTION = "libs"
LICENSE = "Apache-2"

DEPENDS = "gflags c-ares protobuf protobuf-native protobuf-c protobuf-c-native openssl"
DEPENDS_append_class-target = " gtest grpc-native "

LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "https://github.com/grpc/grpc/archive/v1.6.6.tar.gz"
SRC_URI[md5sum] = "a960878ad0231189d48ac8ff17eeca3c"
SRC_URI[sha256sum] = "b97eaa0c8a63b0492dc94bdad621795b4815278e841f06b0c78d6bcbd4c8bdec"

SRC_URI += " \
    file://0001-use-the-right-protoc-executable-regardless-of-protob.patch \
    file://0001-CMakeLists.txt-Fix-libraries-installation-for-Linux.patch \
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

FILES_${PN}-dev += "${libdir}/cmake"

BBCLASSEXTEND = "native"
