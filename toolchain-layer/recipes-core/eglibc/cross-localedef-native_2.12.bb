DESCRIPTION = "Cross locale generation tool for eglibc"
HOMEPAGE = "http://www.eglibc.org/home"
SECTION = "libs"
LICENSE = "LGPL"

LIC_DIR = "${WORKDIR}/${EGLIBC_BRANCH}/libc"
LIC_FILES_CHKSUM = "file://${LIC_DIR}/LICENSES;md5=07a394b26e0902b9ffdec03765209770 \
      file://${LIC_DIR}/COPYING;md5=393a5ca445f6965873eca0259a17f833 \
      file://${LIC_DIR}/posix/rxspencer/COPYRIGHT;md5=dc5485bb394a13b2332ec1c785f5d83a \
      file://${LIC_DIR}/COPYING.LIB;md5=bbb461211a33b134d42ed5ee802b37ff "


inherit native
inherit autotools

PR = "r1"
SRCREV="11982"
EGLIBC_BRANCH="eglibc-2_12"
SRC_URI = "svn://www.eglibc.org/svn/branches/;module=${EGLIBC_BRANCH};proto=http "
S = "${WORKDIR}/${EGLIBC_BRANCH}/localedef"

do_unpack_append() {
	bb.build.exec_func('do_move_ports', d)
}

do_move_ports() {
        if test -d ${WORKDIR}/${EGLIBC_BRANCH}/ports ; then
	    rm -rf ${WORKDIR}/libc/ports
	    mv ${WORKDIR}/${EGLIBC_BRANCH}/ports ${WORKDIR}/libc/
	fi
}

EXTRA_OECONF = "--with-glibc=${WORKDIR}/${EGLIBC_BRANCH}/libc"

do_configure () {
	./configure ${EXTRA_OECONF}
}


do_install() {
	install -d ${D}${bindir} 
	install -m 0755 ${S}/localedef ${D}${bindir}/cross-localedef
}
