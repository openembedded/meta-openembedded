SUMMARY = "Extremely fast non-cryptographic hash algorithm"
DESCRIPTION = "xxHash is an extremely fast non-cryptographic hash algorithm, \
working at speeds close to RAM limits."
HOMEPAGE = "http://www.xxhash.com/"
LICENSE = "BSD-2-Clause & GPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ed3511a67991a5923907dff2ed268026"

SRC_URI = "git://github.com/Cyan4973/xxHash.git"
UPSTREAM_CHECK_GITTAGREGEX = "v(?P<pver>\d+(\.\d+)+)"

SRCREV = "e2f4695899e831171ecd2e780078474712ea61d3"

S = "${WORKDIR}/git"

do_compile () {
	oe_runmake all
}

do_install () {
	oe_runmake DESTDIR=${D} install
}
