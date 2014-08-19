SUMMARY = "Development package for building Applications that use numa"
DESCRIPTION = "Simple NUMA policy support. It consists of a numactl program \
to run other programs with a specific NUMA policy and a libnuma to do \
allocations with NUMA policy in applications."
LICENSE = "GPL-2.0 & LGPL-2.1"
SECTION = "apps"
RDEPENDS_${PN} = "perl"

inherit autotools-brokensep ptest

LIC_FILES_CHKSUM = "file://README;beginline=19;endline=32;md5=5644cc3851cb2499f6c48e52fe198bd9"
SRC_URI[md5sum] = "136685c8eaf9d6569c351fe1d453b30c"
SRC_URI[sha256sum] = "9ca033e6c14c0f26c20379b0cf9299429fd5a354a79c3c7880fd41ef69f7751c"

SRC_URI = "ftp://oss.sgi.com/www/projects/libnuma/download/${BPN}-${PV}.tar.gz \
	   file://fix-null-pointer.patch \
	   file://Fix-the-test-output-format.patch \
	   file://Makefile \
	   file://run-ptest \
          "

# ARM does not currently support NUMA
COMPATIBLE_HOST = "^((?!arm).*)$"

do_install() {
        oe_runmake DESTDIR=${D} prefix=${D}/usr libdir=${D}/${libdir} install
	#remove the empty man2 directory
	rm -r ${D}${mandir}/man2
}

do_install_ptest() {
	#install tests binaries
        local test_binaries="checkaffinity checktopology distance	\
		ftok mbind_mig_pages migrate_pages move_pages mynode	\
		nodemap pagesize prefered printcpu randmap realloc_test	\
		regress regress2 runltp shmtest tbitmap tshared bind_range"

	[ ! -d ${D}/${PTEST_PATH}/test ] && mkdir -p ${D}/${PTEST_PATH}/test
	for i in $test_binaries; do
		install -m 0755 ${B}/test/$i ${D}${PTEST_PATH}/test
	done
	install -m 0755 ${WORKDIR}/Makefile ${D}${PTEST_PATH}/
	install -m 0755 ${B}/numactl ${D}${PTEST_PATH}/
}
