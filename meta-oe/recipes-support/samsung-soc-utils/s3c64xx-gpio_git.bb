SUMMARY = "A user-space tool to show and modify the state of GPIOs on the S3c64xx platform"
SECTION = "console/utils"
AUTHOR = "Werner Almesberger <werner@openmoko.org>"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://gpio-s3c6410.c;endline=12;md5=060cda1be945ad9194593f11d56d55c7"
PV = "1.0+git${SRCPV}"

SRCREV = "0bde889e6fc09a330d0e0b9eb9808b20b2bf13d1"
SRC_URI = "git://github.com/openmoko/openmoko-svn.git;protocol=https;subpath=src/target/gpio"
S = "${WORKDIR}/gpio"

CLEANBROKEN = "1"

do_compile() {
    ${CC} ${CFLAGS} ${LDFLAGS} -o ${PN} gpio-s3c6410.c
}

do_install() {
    install -d ${D}${sbindir}
    install -m 0755 ${PN} ${D}${sbindir}
}
