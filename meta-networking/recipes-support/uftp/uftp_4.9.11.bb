DESCRIPTION = "Encrypted UDP based FTP with multicast"
HOMEPAGE = "https://sourceforge.net/projects/uftp-multicast"
SECTION = "libs/network"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=d32239bcb673463ab874e80d47fae504"

UPSTREAM_CHECK_URI = "https://sourceforge.net/projects/uftp-multicast/files/source-tar/"

SRC_URI = "${SOURCEFORGE_MIRROR}/uftp-multicast/source-tar/uftp-${PV}.tar.gz"
SRC_URI[md5sum] = "c0bc86b3067825a3866de9ef80c7451a"
SRC_URI[sha256sum] = "2ddc835a35f669d55df27ab6883ba19bb00bc8e4b66897ceaedb160b02246b1a"

DEPENDS = "openssl"

do_install () {
	oe_runmake install DESTDIR=${D}
}
