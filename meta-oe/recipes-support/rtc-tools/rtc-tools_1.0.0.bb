SUMMARY = "Useful programs to test rtc drivers"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=74274e8a218423e49eefdea80bc55038"

SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/abelloni/${BPN}.git;protocol=https;branch=master \
           file://0001-rtc-tools-Add-a-Makefile.patch \
           "
SRCREV = "acc442e7af4e1e783432a43d37f1a7938c692659"

S = "${WORKDIR}/git"

EXTRA_OEMAKE = "DESTDIR=${D}"

do_install() {
	oe_runmake install
}
