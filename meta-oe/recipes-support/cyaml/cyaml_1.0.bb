SUMMARY = "Cyaml library"
DESCRIPTION = "LibCYAML is a C library for reading and writing structured YAML documents. It is written in ISO C11 and licensed under the ISC licence."
HOMEPAGE = "https://github.com/tlsa/libcyaml"
SECTION = "libs"
LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://LICENSE;md5=fe6f0e49348c87bddd5d27803dceaaf0"
DEPENDS = " \
    libyaml \
"
SRCREV = "07ff8654a270ec9b410acd3152b60de9e9f941af"

SRC_URI = "git://github.com/tlsa/libcyaml.git;protocol=https;branch=main"

S = "${WORKDIR}/git"

inherit pkgconfig

EXTRA_OEMAKE = "'PREFIX=""' 'DESTDIR=${D}' 'CFLAGS=${CFLAGS}' 'LIBDIR=${libdir}' 'INCLUDEDIR=${includedir}'"

do_compile() {
    oe_runmake VARIANT=release
}
do_install() {
    oe_runmake install VARIANT=release
}

CFLAGS += "-pedantic -Wall -Wextra -O3 -Iinclude"