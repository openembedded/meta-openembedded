DESCRIPTION = "CppUnit is a C++ unit testing framework"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=7fbc338309ac38fefcd64b04bb903e34"

SRC_URI = "${SOURCEFORGE_MIRROR}/cppunit/cppunit-${PV}.tar.gz"
SRC_URI[md5sum] = "bd30e9cf5523cdfc019b94f5e1d7fd19"
SRC_URI[sha256sum] = "ac28a04c8e6c9217d910b0ae7122832d28d9917fa668bcc9e0b8b09acb4ea44a"

inherit autotools binconfig

# Fix a linker ordering problem, we want -ldl *after* -lstdc++
do_configure_append() {
	for i in $(find ${S} -name "Makefile") ; do
		sed -i -e 's:-lm:-lm -ldl:g' $i
	done
}
