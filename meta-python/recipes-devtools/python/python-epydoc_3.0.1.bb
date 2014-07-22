DESCRIPTION = "Epydoc is a tool for generating API documentation for Python modules, based on their docstrings" 
SECTION = "devel/python" 
LICENSE = "MIT" 
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=011c0d78eb6054d731c879132d7313f0"
HOMEPAGE = "http://epydoc.sourceforge.net/" 
SRCNAME = "epydoc" 
DEPENDS += "python"
DEPENDS_virtclass-native += "python-native"

SRC_URI = "http://downloads.sourceforge.net/project/${SRCNAME}/${SRCNAME}/${PV}/${SRCNAME}-${PV}.tar.gz" 
SRC_URI[md5sum] = "cdd6f6c76dd8bab5e653a343a0544294"
SRC_URI[sha256sum] = "d4e5c8d90937d01b05170f592c1fa9b29e9ed0498dfe7f0eb2a3af61725b6ad1"

S = "${WORKDIR}/${SRCNAME}-${PV}" 

inherit distutils 

BBCLASSEXTEND = "native"
