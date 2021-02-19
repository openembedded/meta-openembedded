DESCRIPTION = "A high performance, open source, general-purpose RPC framework. \
Provides gRPC libraries for multiple languages written on top of shared C core library \
(C++, Node.js, Python, Ruby, Objective-C, PHP, C#)"
HOMEPAGE = "https://github.com/grpc/grpc"
SECTION = "libs"
LICENSE = "Apache-2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

DEPENDS = "gflags c-ares protobuf protobuf-native protobuf-c protobuf-c-native openssl libnsl2 abseil-cpp re2"
DEPENDS_append_class-target = " googletest grpc-native "
DEPENDS_append_class-nativesdk = " grpc-native "

S = "${WORKDIR}/git"
SRCREV_grpc = "627a22541a1836ce00cdc40a3977aa8928de98bc"
BRANCH = "v1.35.x"
SRC_URI = "git://github.com/grpc/grpc.git;protocol=https;name=grpc;branch=${BRANCH} \
           "
# Fixes build with older compilers 4.8 especially on ubuntu 14.04
CXXFLAGS_append_class-native = " -Wl,--no-as-needed"

inherit cmake pkgconfig

EXTRA_OECMAKE = " \
    -DgRPC_CARES_PROVIDER=package \
    -DgRPC_ZLIB_PROVIDER=package \
    -DgRPC_SSL_PROVIDER=package \
    -DgRPC_PROTOBUF_PROVIDER=package \
    -DgRPC_GFLAGS_PROVIDER=package \
    -DgRPC_ABSL_PROVIDER=package \
    -DgRPC_RE2_PROVIDER=package \
    -DgRPC_INSTALL=ON \
    -DCMAKE_CROSSCOMPILING=ON \
    -DBUILD_SHARED_LIBS=ON \
    -DgRPC_INSTALL_LIBDIR=${baselib} \
    -DgRPC_INSTALL_CMAKEDIR=${baselib}/cmake/${BPN} \
    "

do_configure_prepend_mipsarch() {
    sed -i -e "s/set(_gRPC_ALLTARGETS_LIBRARIES \${CMAKE_DL_LIBS} rt m pthread)/set(_gRPC_ALLTARGETS_LIBRARIES \${CMAKE_DL_LIBS} atomic rt m pthread)/g" ${S}/CMakeLists.txt
}

do_configure_prepend_powerpc() {
    sed -i -e "s/set(_gRPC_ALLTARGETS_LIBRARIES \${CMAKE_DL_LIBS} rt m pthread)/set(_gRPC_ALLTARGETS_LIBRARIES \${CMAKE_DL_LIBS} atomic rt m pthread)/g" ${S}/CMakeLists.txt
}

do_configure_prepend_riscv64() {
    sed -i -e "s/set(_gRPC_ALLTARGETS_LIBRARIES \${CMAKE_DL_LIBS} rt m pthread)/set(_gRPC_ALLTARGETS_LIBRARIES \${CMAKE_DL_LIBS} atomic rt m pthread)/g" ${S}/CMakeLists.txt
}

do_configure_prepend_riscv32() {
    sed -i -e "s/set(_gRPC_ALLTARGETS_LIBRARIES \${CMAKE_DL_LIBS} rt m pthread)/set(_gRPC_ALLTARGETS_LIBRARIES \${CMAKE_DL_LIBS} atomic rt m pthread)/g" ${S}/CMakeLists.txt
}

do_configure_prepend_toolchain-clang_x86() {
    sed -i -e "s/set(_gRPC_ALLTARGETS_LIBRARIES \${CMAKE_DL_LIBS} rt m pthread)/set(_gRPC_ALLTARGETS_LIBRARIES \${CMAKE_DL_LIBS} atomic rt m pthread)/g" ${S}/CMakeLists.txt
}

BBCLASSEXTEND = "native nativesdk"

SYSROOT_DIRS_BLACKLIST_append_class-target = " ${baselib}/cmake/grpc"

FILES_${PN}-dev += "${bindir}"
