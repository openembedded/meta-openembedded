DESCRIPTION = "htop process monitor"
HOMEPAGE = "http://htop.sf.net"
SECTION = "console/utils"
LICENSE = "GPLv2"

LIC_FILES_CHKSUM = "file://COPYING;md5=c312653532e8e669f30e5ec8bdc23be3"

DEPENDS = "ncurses"
RDEPENDS_${PN} = "ncurses-terminfo"

SRC_URI = "${SOURCEFORGE_MIRROR}/htop/htop-${PV}.tar.gz"

LDFLAGS_append_libc-uclibc = " -lubacktrace"

inherit autotools

SRC_URI[md5sum] = "0d01cca8df3349c74569cefebbd9919e"
SRC_URI[sha256sum] = "ee60657b044ece0df096c053060df7abf3cce3a568ab34d260049e6a37ccd8a1"

