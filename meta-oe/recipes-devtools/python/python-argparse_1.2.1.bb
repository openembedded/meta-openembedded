DESCRIPTION = "Python command-line parsing library"
SECTION = "devel/python"
LICENSE = "PSF"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=09d08bb5b7047e2688ea3faad6408aa8"
SRCNAME = argparse

SRC_URI = "http://argparse.googlecode.com/files/${SRCNAME}-${PV}.tar.gz"
SRC_URI[md5sum] = "2fbef8cb61e506c706957ab6e135840c"
SRC_URI[sha256sum] = "ddaf4b0a618335a32b6664d4ae038a1de8fbada3b25033f9021510ed2b3941a4"

S = "${WORKDIR}/${SRCNAME}-${PV}"

inherit setuptools

BBCLASSEXTEND = "native"
