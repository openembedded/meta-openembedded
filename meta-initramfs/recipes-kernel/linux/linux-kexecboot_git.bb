require linux-kexecboot.inc

PR = "${INC_PR}.1"

KERNEL_RELEASE = "3.2.0-rc7"
OLD_KERNEL_RELEASE = "3.1.99"
PV = "${OLD_KERNEL_RELEASE}+${KERNEL_RELEASE}+gitr${SRCPV}"

SRCREV = "371de6e4e0042adf4f9b54c414154f57414ddd37"

CMDLINE_DEBUG = "debug"

S = "${WORKDIR}/git"
SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/torvalds/linux-2.6.git;protocol=git;branch=master"

do_configure_prepend() {
#otherwise it gets extra '+' "2.6.37+"
#because:
#$ scripts/setlocalversion . => +
#$ make kernelversion => 2.6.37
#$ make kernelrelease => 2.6.37+
  rm -rf ${S}/.git
}
