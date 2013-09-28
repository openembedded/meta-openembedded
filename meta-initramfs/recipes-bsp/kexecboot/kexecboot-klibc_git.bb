PV = "0.5.9"
PR = "r10+gitr${SRCREV}"

SRC_URI = "git://github.com/kexecboot/kexecboot.git"
SRCREV = "0bcc14671aa2d84ec20f260582aca36bb901ccf1"
S = "${WORKDIR}/git"

# the binary is statically linked against klibc
inherit klibc

require kexecboot.inc
SUMMARY = "kexecboot linux-as-bootloader, statically compiled against klibc"
