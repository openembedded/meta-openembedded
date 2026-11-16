DESCRIPTION = "Google gRPC tools"
HOMEPAGE = "https://www.grpc.io/"
SECTION = "devel/python"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://PKG-INFO;beginline=6;endline=6;md5=0bcf70029ff7f40a132bf95381ee0b2d"

inherit pypi python_setuptools_build_meta cython

DEPENDS += "python3-grpcio python3-wheel-native"

SRC_URI += "file://0001-abseil-raw_hash_set-include-immintrin.h-instead-of-b.patch"
SRC_URI[sha256sum] = "d6f1ee213a07de9462c90ee5debe4a78dff20306a0fb394a65d48b2f94fe31a1"

RDEPENDS:${PN} = "python3-grpcio"

do_configure:append() {
    sed -i 's/"Cython==/"Cython>=/' ${S}/pyproject.toml
}

do_compile:prepend() {
    export GRPC_PYTHON_BUILD_EXT_COMPILER_JOBS="${@oe.utils.parallel_make(d, False)}"
}

BBCLASSEXTEND = "native nativesdk"

CVE_PRODUCT += "grpc:grpc"
