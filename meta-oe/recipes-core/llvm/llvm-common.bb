DESCRIPTION = "Helper script for OE's llvm support"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420 \
"

SRC_URI = "file://llvm-config"

do_install() {
    install -d ${SYSROOT_DESTDIR}${bindir_crossscripts}/
    install -m 0755 ${WORKDIR}/llvm-config ${SYSROOT_DESTDIR}${bindir_crossscripts}/
}

do_install_virtclass-native() {
    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/llvm-config ${D}${bindir}
}

BBCLASSEXTEND = "native"
