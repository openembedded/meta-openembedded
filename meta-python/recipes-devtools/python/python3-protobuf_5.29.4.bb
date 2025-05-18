DESCRIPTION = "Protocol Buffers"
HOMEPAGE = "https://developers.google.com/protocol-buffers/"
SECTION = "devel/python"
# MIT license is from utf8_range although it's packaged without this information
# https://github.com/protocolbuffers/protobuf/blob/main/third_party/utf8_range/LICENSE
LICENSE = "BSD-3-Clause & MIT"
LIC_FILES_CHKSUM = " \
    file://PKG-INFO;beginline=9;endline=9;md5=53dbfa56f61b90215a9f8f0d527c043d \
    file://utf8_range/utf8_range.h;beginline=1;endline=2;md5=f05b3ef36cc354d7134597075a1af64f \
"

inherit pypi setuptools3
SRC_URI[sha256sum] = "4f1dfcd7997b31ef8f53ec82781ff434a28bf71d9102ddde14d076adcfc78c99"

CVE_PRODUCT += "google:protobuf protobuf:protobuf google-protobuf protobuf-python"

# http://errors.yoctoproject.org/Errors/Details/184715/
# Can't find required file: ../src/google/protobuf/descriptor.proto
CLEANBROKEN = "1"

DEPENDS += "protobuf"

RDEPENDS:${PN} += " \
    python3-ctypes \
    python3-datetime \
    python3-json \
    python3-logging \
    python3-netclient \
    python3-numbers \
    python3-pkgutil \
    python3-unittest \
"

# For usage in other recipies when compiling protobuf files (e.g. by grpcio-tools)
BBCLASSEXTEND = "native nativesdk"

DISTUTILS_BUILD_ARGS += "--cpp_implementation"
DISTUTILS_INSTALL_ARGS += "--cpp_implementation"

do_compile:prepend:class-native () {
    export KOKORO_BUILD_NUMBER="1"
}

do_install:append () {
    # Remove useless and problematic .pth file. python3-protobuf is installed in the standard
    # location of site packages. No need for such .pth file.
    # NOTE: do not drop this removal until the following issue in upstream cpython is resolved:
    # https://github.com/python/cpython/issues/122220
    rm -f ${D}${PYTHON_SITEPACKAGES_DIR}/protobuf-*-nspkg.pth
}
