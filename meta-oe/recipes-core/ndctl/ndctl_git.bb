SUMMARY = "libnvdimm utility library"
DESCRIPTION = "Utility library for managing the libnvdimm \
(non-volatile memory device) sub-system in the Linux kernel. \
The LIBNVDIMM subsystem provides support for three types of \
NVDIMMs, namely,PMEM, BLK, and NVDIMM devices that can \
simultaneously support both PMEM and BLK mode access."
HOMEPAGE = "https://git.kernel.org/cgit/linux/kernel/git/nvdimm/nvdimm.git/tree/Documentation/nvdimm/nvdimm.txt?h=libnvdimm-for-next"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=e66651809cac5da60c8b80e9e4e79e08"

inherit autotools-brokensep pkgconfig module-base

SRCREV = "0d6aeeabec9d271e08c12e4cf679b59946e20156"
SRC_URI = "git://github.com/pmem/ndctl.git"

DEPENDS = "virtual/kernel kmod udev json-c"

PV = "v61+git${SRCPV}"
S = "${WORKDIR}/git"

EXTRA_OECONF += "--enable-test --enable-destructive --disable-docs"

do_configure_prepend() {
    ${S}/autogen.sh
}

COMPATIBLE_HOST='(x86_64).*'

FILES_${PN} += "/usr/share/bash-completion/completions/ndctl"
