RDEPENDS_${PN} = "kexec-klibc"
PV = "0.5.9"
PR = "r8+gitr${SRCREV}"

SRC_URI = "git://git.linuxtogo.org/home/groups/kexecboot/kexecboot.git;protocol=git "
SRCREV = "8f1d3cc3d9c650c6a6c88f83071185d6ee0ef9c7"
S = "${WORKDIR}/git"

# the binary is statically linked against klibc
inherit klibc

require kexecboot.inc
