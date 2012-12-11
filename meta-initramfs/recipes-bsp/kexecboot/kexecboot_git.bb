RDEPENDS_${PN} = "kexec"
PV = "0.5.9"
PR = "r10+gitr${SRCREV}"

SRC_URI = "git://github.com/kexecboot/kexecboot.git;protocol=git"
SRCREV = "b667914bb0cba1c30f9b771abccfe98f1419afeb"
S = "${WORKDIR}/git"

require kexecboot.inc
