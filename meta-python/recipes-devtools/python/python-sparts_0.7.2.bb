SUMMARY = "Library for rapid service prototyping with minimal boilerplate"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=0c1554636a564da3baf037ac652fc831"
SRCNAME = "sparts"

SRC_URI = "git://github.com/facebook/sparts.git"
SRCREV = "128cf9c2f82b00cb2d8648033970870c73641f02"

S = "${WORKDIR}/git"

inherit setuptools

RDEPENDS_${PN} = "\
               python-subprocess \
               python-six \
               python-argparse \
               python-re \
               python-shell \
               python-threading \
               python-logging \
               python-unixadmin \
               python-distutils \
               python-daemonize \
               python-futures \
"
