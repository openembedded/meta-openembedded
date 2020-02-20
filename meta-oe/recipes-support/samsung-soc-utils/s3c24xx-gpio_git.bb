SUMMARY = "A user-space tool to show and modify the state of GPIOs on the S3c24xx platform"
SECTION = "console/utils"
AUTHOR = "Werner Almesberger <werner@openmoko.org>"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://gpio.c;endline=12;md5=cfb91c686857b2e60852b4925d90a3e1"
PV = "1.0+git${SRCPV}"

SRCREV = "0bde889e6fc09a330d0e0b9eb9808b20b2bf13d1"
SRC_URI = "git://github.com/openmoko/openmoko-svn.git;protocol=https;subpath=src/target/gpio"
S = "${WORKDIR}/gpio"

CLEANBROKEN = "1"

do_compile() {
    ${CC} ${CFLAGS} ${LDFLAGS} -o ${PN} gpio.c
}

do_install() {
    install -d ${D}${sbindir}
    install -m 0755 ${PN} ${D}${sbindir}
}
