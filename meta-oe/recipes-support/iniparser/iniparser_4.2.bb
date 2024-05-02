SUMMARY = "The iniParser library is a simple C library offering INI file parsing services (both reading and writing)."
SECTION = "libs"
HOMEPAGE = "https://github.com/ndevilla/iniparser"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8474d3b745f77e203f1fc82fb0bb7678"

DEPENDS = "doxygen-native"

PV .= "+git"

SRC_URI = "git://github.com/ndevilla/iniparser.git;protocol=https;branch=master \
           file://0001-iniparser.pc-Make-libpath-a-variable.patch \
           file://Add-CMake-support.patch \
"

SRCREV = "9f5a6da1c245b44f49a46212ec0d81ffb1f821aa"

S = "${WORKDIR}/git"

inherit cmake

do_install:append() {
    install -Dm 0644 ${S}/iniparser.pc ${D}${libdir}/pkgconfig/iniparser.pc
    sed -i -e 's,@baselib@,${baselib},g' ${D}${libdir}/pkgconfig/iniparser.pc
}

BBCLASSEXTEND += "native"
