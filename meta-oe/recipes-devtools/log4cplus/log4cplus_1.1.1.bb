SUMMARY = "log4cplus provides a simple C++ logging API for log management"
SECTION = "libs"
HOMEPAGE = "http://sourceforge.net/projects/log4cplus/"
BUGTRACKER = "http://sourceforge.net/p/log4cplus/bugs/"

LICENSE = "Apache-2.0 BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=65053a46f707ee2b93f09ad22f692170"

SRC_URI = "${SOURCEFORGE_MIRROR}/project/${BPN}/${BPN}-stable/${PV}/${BP}.tar.gz"
SRC_URI[md5sum] = "104bd6dd07ee71bc52ee9adca4d4d5fc"
SRC_URI[sha256sum] = "96905e763fc6f1e3a854c3d1964c21e877de909bdddd0aed99806c62a68be838"

inherit autotools pkgconfig

BBCLASSEXTEND += "native"
