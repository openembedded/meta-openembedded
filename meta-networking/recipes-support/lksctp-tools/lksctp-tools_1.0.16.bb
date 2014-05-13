SUMMARY = "The Linux Kernel Stream Control Transmission Protocol (lksctp) project"
SECTION = "libs"
LICENSE = "LGPLv2"

LIC_FILES_CHKSUM = " \
    file://COPYING.lib;md5=0a1b79af951c42a9c8573533fbba9a92 \
    file://COPYING;md5=0c56db0143f4f80c369ee3af7425af6e \
"

SRC_URI = "${SOURCEFORGE_MIRROR}/lksctp/${BP}.tar.gz"

SRC_URI[md5sum] = "708bb0b5a6806ad6e8d13c55b067518e"
SRC_URI[sha256sum] = "0903dd526b7f30a89d5031aa2c82757612becc38ed7bc6e4f972f8deae351f26"

S = "${WORKDIR}/${BP}"

BBCLASSEXTEND = "native"

inherit autotools pkgconfig binconfig

SOLIBVERSION="${PV}"
SOLIBMAJORVERSION="1"

PACKAGES =+ "${PN}-withsctp ${PN}-utils"

FILES_${PN} = " \
    ${libdir}/libsctp.so.${SOLIBVERSION} \
"

FILES_${PN}-withsctp = " \
    ${libdir}/lksctp-tools/libwithsctp.so.${SOLIBVERSION} \
"

FILES_${PN}-dev += " \
    ${libdir}/libsctp.so.${SOLIBMAJORVERSION} \
    ${libdir}/libsctp.so \
    ${libdir}/lksctp-tools/libwithsctp.so.${SOLIBMAJORVERSION} \
    ${libdir}/lksctp-tools/libwithsctp.so \
    ${datadir}/lksctp-tools/*.c \
    ${datadir}/lksctp-tools/*.h \
"

FILES_${PN}-utils = "${bindir}/*"
