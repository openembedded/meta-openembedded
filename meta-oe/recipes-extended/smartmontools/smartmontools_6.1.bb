SECTION = "console/utils"
DESCRIPTION = "Control and monitor storage systems using S.M.A.R.T."
HOMEPAGE = "http://smartmontools.sourceforge.net/"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI = "${SOURCEFORGE_MIRROR}/smartmontools/smartmontools-${PV}.tar.gz"
SRC_URI[md5sum] = "83a3a681f8183ed858392d550ae1cca6"
SRC_URI[sha256sum] = "a9003b8bccc82682f658ce76d70edb1842411e51dc56d4cd6b56618da1d9ce07"

inherit autotools

