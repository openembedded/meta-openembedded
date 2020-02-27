DESCRIPTION = "Encrypted UDP based FTP with multicast"
HOMEPAGE = "https://sourceforge.net/projects/uftp-multicast"
SECTION = "libs/network"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=d32239bcb673463ab874e80d47fae504"

UPSTREAM_CHECK_URI = "https://sourceforge.net/projects/uftp-multicast/files/source-tar/"

SRC_URI = "${SOURCEFORGE_MIRROR}/uftp-multicast/source-tar/uftp-${PV}.tar.gz"
SRC_URI[md5sum] = "a5c79c8e5a20d0646c9579fe1f339493"
SRC_URI[sha256sum] = "903353febf1b88f701198d956a60cec3d2be3cc251561a678061761aec6622f6"

DEPENDS = "openssl"

do_install () {
	oe_runmake install DESTDIR=${D}
}
