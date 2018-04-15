DESCRIPTION = "TinyALSA is a small library to interface with ALSA in \
the Linux kernel. It is a lightweight alternative to libasound."
HOMEPAGE = "https://github.com/tinyalsa/tinyalsa"
SECTION = "libs/multimedia"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://NOTICE;md5=dbdefe400d894b510a9de14813181d0b"

SRC_URI = "https://github.com/tinyalsa/tinyalsa/archive/${PV}.tar.gz"
SRC_URI[md5sum] = "ec5c1cc175fcb8c9d3d0adcececf10a9"
SRC_URI[sha256sum] = "d92b438cea348ae316c4b2cbf367b4f01ed821a947e6a34d813624e9e3c936c1"

do_configure() {
    :
}

do_compile() {
    oe_runmake CC='${CC}' LD='${CC}' AR='${AR}'
}

do_install() {
    oe_runmake install \
        PREFIX="${prefix}" DESTDIR="${D}" INCDIR="${includedir}/tinyalsa" \
        LIBDIR="${libdir}" BINDIR="${bindir}" MANDIR="${mandir}"
}

PACKAGES =+ "${PN}-tools"
FILES_${PN}-tools = "${bindir}/*"
