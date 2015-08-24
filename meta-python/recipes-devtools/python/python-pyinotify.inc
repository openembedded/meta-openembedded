DESCRIPTION = "Python pyinotify: Linux filesystem events monitoring"
HOMEPAGE = "https://pypi.python.org/pypi/pyinotify"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=ab173cade7965b411528464589a08382"

RDEPENDS_${PN} += "python-threading python-io python-subprocess python-misc python-shell"

SRCNAME = "pyinotify"
SRC_URI = "https://pypi.python.org/packages/source/p/${SRCNAME}/${SRCNAME}-${PV}.tar.gz"

SRC_URI[md5sum] = "8e580fa1ff3971f94a6f81672b76c406"
SRC_URI[sha256sum] = "9c998a5d7606ca835065cdabc013ae6c66eb9ea76a00a1e3bc6e0cfe2b4f71f4"

S = "${WORKDIR}/${SRCNAME}-${PV}"
