SUMMARY = "Cyaml library"
DESCRIPTION = "LibCYAML is a library for reading and writing structured YAML documents."
HOMEPAGE = "https://github.com/tlsa/libcyaml"
SECTION = "libs"
LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://LICENSE;md5=fe6f0e49348c87bddd5d27803dceaaf0"
DEPENDS = " \
    libyaml \
"
SRCREV = "d05365e474e56d34c8d4b25599a333c0635eeebf"

SRC_URI = "git://github.com/tlsa/libcyaml.git;protocol=https;branch=main-v1;tag=v${PV}"


inherit pkgconfig

EXTRA_OEMAKE = "'PREFIX=""' 'DESTDIR=${D}' 'CFLAGS=${CFLAGS}' 'LIBDIR=${libdir}' 'INCLUDEDIR=${includedir}' 'VARIANT=release'"

do_compile() {
    oe_runmake
}
do_install() {
    oe_runmake install
}

CFLAGS += "-pedantic -Wall -Wextra -O3 -Iinclude"