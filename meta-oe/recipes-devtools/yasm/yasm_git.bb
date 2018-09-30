SUMMARY = "x86 (SSE) assembler supporting NASM and GAS-syntaxes"
LICENSE = "BSD"
HOMEPAGE = "http://www.tortall.net/projects/yasm/"

LIC_FILES_CHKSUM = "file://COPYING;md5=a12d8903508fb6bfd49d8d82c6170dd9"

PV = "1.3.0+git${SRCPV}"
SRCREV = "35af9720e36df19382a57be26643b0d6bb48a363"
SRC_URI = "git://github.com/yasm/yasm.git"

S = "${WORKDIR}/git"

inherit autotools gettext

export CCLD_FOR_BUILD = "${CC_FOR_BUILD}"

BBCLASSEXTEND = "native"
