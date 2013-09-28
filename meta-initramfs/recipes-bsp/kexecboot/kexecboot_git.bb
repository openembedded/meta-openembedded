PV = "0.5.9.1"

SRC_URI = "git://github.com/kexecboot/kexecboot.git"
SRCREV = "0bcc14671aa2d84ec20f260582aca36bb901ccf1"
S = "${WORKDIR}/git"

require kexecboot.inc

BBCLASSEXTEND = "klibc"
