DESCRIPTION = "x86 (SSE) assembler supporting NASM and GAS-syntaxes"
LICENSE = "BSD"
HOMEPAGE = "http://www.tortall.net/projects/yasm/"

LIC_FILES_CHKSUM = "file://COPYING;md5=26c9f3d11f88911950f9ff62189d3d4f"

SRC_URI = "http://www.tortall.net/projects/yasm/releases/yasm-${PV}.tar.gz"
SRC_URI[md5sum] = "4cfc0686cf5350dd1305c4d905eb55a6"
SRC_URI[sha256sum] = "768ffab457b90a20a6d895c39749adb547c1b7cb5c108e84b151a838a23ccf31"

S = "${WORKDIR}/yasm-${PV}"

inherit autotools gettext

BBCLASSEXTEND = "native"
