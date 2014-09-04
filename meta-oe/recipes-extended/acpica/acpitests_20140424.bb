SUMMARY = "Test suite used to validate ACPICA"
HOMEPAGE = "http://www.acpica.org/"

LICENSE = "Intel"
LIC_FILES_CHKSUM = "file://tests/aapits/atexec.c;beginline=1;endline=115;md5=e92bcdfcd01d117d1bda3e814bb2030a"

DEPENDS = "bison flex"

SRC_URI = "https://acpica.org/sites/acpica/files/acpitests-unix-${PV}.tar.gz;name=acpitests \
           https://acpica.org/sites/acpica/files/acpica-unix2-${PV}.tar.gz;name=acpica \
           file://aapits-linux.patch \
           file://aapits-makefile.patch \
    "
SRC_URI[acpitests.md5sum] = "bfc399cecb21e9491d362d8e480b2689"
SRC_URI[acpitests.sha256sum] = "e7d7ff638872543909b38c2498e88958251ee6ce4d22bc13d4e3925771212c0e"
SRC_URI[acpica.md5sum] = "733532f005fba5d1c5344440651b13d1"
SRC_URI[acpica.sha256sum] = "72ece982bbbdfb1b17418f1feb3a9daaa01803d0d41dcf00e19d702cdf751bbc"

S = "${WORKDIR}/acpitests-unix-${PV}"

EXTRA_OEMAKE = "'CC=${TARGET_PREFIX}gcc ${HOST_CC_ARCH}' 'OPT_CFLAGS=-Wall'"

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
