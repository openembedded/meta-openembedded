RDEPENDS_${PN} = "kexec-klibc"
PV = "0.5.9"
PR = "r10+gitr${SRCREV}"

SRC_URI = "git://github.com/kexecboot/kexecboot.git;protocol=git"
SRCREV = "b667914bb0cba1c30f9b771abccfe98f1419afeb"
S = "${WORKDIR}/git"

# the binary is statically linked against klibc
inherit klibc

require kexecboot.inc
