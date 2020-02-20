SUMMARY = "JTAG utility to interface w/ a S3C2410 device"
SECTION = "devel"
AUTHOR = "Harald Welte <laforge@openmoko.org>"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://parport.c;endline=19;md5=b5681091b0fd8c5f7068835c441bf0c8"
PV = "1.0+git${SRCPV}"

SRCREV = "0bde889e6fc09a330d0e0b9eb9808b20b2bf13d1"
SRC_URI = "git://github.com/openmoko/openmoko-svn.git;protocol=https;subpath=src/host/sjf2410-linux \
    file://0001-ppt.c-Do-not-include-sys-io.h.patch \
"
S = "${WORKDIR}/sjf2410-linux"

inherit native deploy
do_deploy[sstate-outputdirs] = "${DEPLOY_DIR_TOOLS}"

CFLAGS += "-DLINUX_PPDEV"

do_compile() {
    oe_runmake
}

do_install() {
    install -d ${D}/${bindir}
    install -m 0755 sjf2410 ${D}/${bindir}
}

do_deploy() {
    install -m 0755 sjf2410 ${DEPLOYDIR}/sjf2410-${PV}
}

addtask deploy before do_build after do_install
