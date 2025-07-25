DESCRIPTION = "Google's framework for writing C++ tests"
HOMEPAGE = "https://github.com/google/googletest"
SECTION = "libs"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=cbbd27594afd089daa160d3a16dd515a"

PROVIDES += "gmock gtest"

SRCREV = "6910c9d9165801d8827d628cb72eb7ea9dd538c5"
SRC_URI = "git://github.com/google/googletest.git;branch=v1.16.x;protocol=https \
    file://gtest-ciso646.patch \
"

inherit cmake pkgconfig

# allow for shared libraries, but do not default to them
#
PACKAGECONFIG[shared] = "-DBUILD_SHARED_LIBS=ON,-DBUILD_SHARED_LIBS=OFF,,"

CXXFLAGS:append = " -fPIC"

ALLOW_EMPTY:${PN} = "1"
ALLOW_EMPTY:${PN}-dbg = "1"

# -staticdev will not be implicitly put into an SDK, so we add an rdepend
# if we are not building shared libraries
#
RDEPENDS:${PN}-dev += "${@bb.utils.contains("PACKAGECONFIG","shared","","${PN}-staticdev",d)}"

BBCLASSEXTEND = "native nativesdk"

do_configure:prepend() {
    # explicitly use python3
    # the scripts are already python3 compatible since https://github.com/google/googletest/commit/d404af0d987a9c38cafce82a7e26ec8468c88361 and other fixes like this
    # but since this oe-core change http://git.openembedded.org/openembedded-core/commit/?id=5f8f16b17f66966ae91aeabc23e97de5ecd17447
    # there isn't python in HOSTTOOLS so "env python" fails
    sed -i 's@^#!/usr/bin/env python$@#!/usr/bin/env python3@g' ${S}/googlemock/test/*py ${S}/googletest/test/*py
}
