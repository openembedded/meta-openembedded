SUMMARY = "Simple top-like I/O monitor"
DESCRIPTION = "iotop does for I/O usage what top(1) does for CPU usage. \
    It watches I/O usage information output by the Linux kernel and displays \
    a table of current I/O usage by processes on the system."
HOMEPAGE = "http://guichaz.free.fr/iotop/"

PR = "r1"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=4325afd396febcb659c36b49533135d4"

SRC_URI = "http://guichaz.free.fr/iotop/files/${BP}.tar.bz2"
SRC_URI[md5sum] = "cdd38b276cbf238676f5d2bcf098ba9c"
SRC_URI[sha256sum] = "46f3279fb1a7dfc129b5d00950c6e8389e4aedeb58880e848b88d686483df0b0"

inherit distutils

do_install_append() {
    rm -f ${D}${PYTHON_SITEPACKAGES_DIR}/site.pyo || true
    rm -f ${D}${PYTHON_SITEPACKAGES_DIR}/site.py  || true
}

RDEPENDS_${PN} = "python-curses python-textutils \
                  python-codecs python-ctypes python-pprint \
                  python-shell"
