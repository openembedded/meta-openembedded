SUMMARY = "Filesystem and hardware benchmark and stress tool"
DESCRIPTION = "fio is an I/O tool meant to be used both for benchmark and \
stress/hardware verification. It has support for a number of I/O engines, \
I/O priorities (for newer Linux kernels), rate I/O, forked or threaded jobs, \
and much more. It can work on block devices as well as files. fio accepts \
job descriptions in a simple-to-understand text format. Several example job \
files are included. fio displays all sorts of I/O performance information."
HOMEPAGE = "http://freecode.com/projects/fio"
SECTION = "console/tests"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=393a5ca445f6965873eca0259a17f833"

DEPENDS = "libaio"

# rev for v2.0.5
SRCREV = "02efadeb8b05144bcf2fc7796e1da2e7db211d00"
SRC_URI = "git://git.kernel.dk/fio.git \
           file://0001-ARM-Use-generic-assembly-nop-and-barrier-code-for-ar.patch"

S = "${WORKDIR}/git"

EXTRA_OEMAKE = "CC='${CC}' CFLAGS='${CFLAGS}' LDFLAGS='${LDFLAGS}'"

do_install() {
    oe_runmake install DESTDIR=${D} bindir=${bindir} mandir=${mandir}
    install -d ${D}/${docdir}/${PN}
    cp -a ${S}/examples ${D}/${docdir}/${PN}/
}
