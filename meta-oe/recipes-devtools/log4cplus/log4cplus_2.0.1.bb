SUMMARY = "log4cplus provides a simple C++ logging API for log management"
SECTION = "libs"
HOMEPAGE = "http://sourceforge.net/projects/log4cplus/"
BUGTRACKER = "http://sourceforge.net/p/log4cplus/bugs/"

LICENSE = "Apache-2.0 & BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=41e8e060c26822886b592ab4765c756b"

SRC_URI = "${SOURCEFORGE_MIRROR}/project/${BPN}/${BPN}-stable/${PV}/${BP}.tar.gz"
SRC_URI[md5sum] = "2b4054b2d66c0ef0e4091df26ce8aad4"
SRC_URI[sha256sum] = "772b268142e1c02dd346880fff06f38f693e9458d6390ce0b493395d0ca01714"

UPSTREAM_CHECK_URI = "https://sourceforge.net/projects/log4cplus/files/log4cplus-stable/"
UPSTREAM_CHECK_REGEX = "log4cplus-stable/(?P<pver>\d+(\.\d+)+)/"

inherit autotools pkgconfig

BBCLASSEXTEND = "native"
