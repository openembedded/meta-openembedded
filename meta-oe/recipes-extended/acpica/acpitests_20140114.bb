SUMMARY = "Test suite used to validate ACPICA"
HOMEPAGE = "http://www.acpica.org/"

LICENSE = "Intel"
LIC_FILES_CHKSUM = "file://tests/aapits/atexec.c;beginline=1;endline=115;md5=e92bcdfcd01d117d1bda3e814bb2030a"

DEPENDS = "bison flex"

SRC_URI = "https://acpica.org/sites/acpica/files/acpitests-unix-${PV}.tar.gz \
           https://acpica.org/sites/acpica/files/acpica-unix2-${PV}.tar.gz \
           file://0001-Fixup-aapits-build.patch \
    "
SRC_URI[md5sum] = "c72b61e092d2b25726dfff6e455116c9"
SRC_URI[sha256sum] = "368f69296edef5138fd33affa2d50bdcdd1dfd2f6919ba782f8ecf03971a3a2c"

S = "${WORKDIR}/acpitests-unix-${PV}"

EXTRA_OEMAKE = "CC=${TARGET_PREFIX}gcc 'OPT_CFLAGS=-Wall'"

# The Makefiles expect a specific layout
do_compile() {
    cp -af ${WORKDIR}/acpica-unix2-${PV}/source ${S}
    cd tests/aapits
    oe_runmake
}

do_install() {
    install -d ${D}${bindir}
    install -m0755 tests/aapits/bin/aapits ${D}${bindir}
}

COMPATIBLE_HOST = "(i.86|x86_64|arm|aarch64).*-linux"
