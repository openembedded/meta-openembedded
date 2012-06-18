RDEPENDS_${PN} = "kexec-klibc"
PV = "0.5.9"
PR = "r9+gitr${SRCREV}"

SRC_URI = "git://github.com/kexecboot/kexecboot.git;protocol=git"
SRCREV = "0daa774eac019602cd89048961e95985ea50dadf"
S = "${WORKDIR}/git"

# the binary is statically linked against klibc
inherit klibc

require kexecboot.inc
