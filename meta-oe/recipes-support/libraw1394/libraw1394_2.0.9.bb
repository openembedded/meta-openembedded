DESCRIPTION = "base library for low-level IEEE 1394 accesses"
HOMEPAGE = "https://ieee1394.wiki.kernel.org/index.php/Libraries#libraw1394"
SECTION = "libs"
LICENSE = "LGPL-2.1"
LIC_FILES_CHKSUM = "file://COPYING.LIB;md5=d8045f3b8f929c1cb29a1e3fd737b499"

SRC_URI = "https://www.kernel.org/pub/linux/libs/ieee1394/${P}.tar.gz"
SRC_URI[md5sum] = "faf6b18a92075d80e45b603f9a659670"
SRC_URI[sha256sum] = "f83268b3f5d37e8d42ae9881c099b55839b70ac0af71549be7ef6af2578e809a"

inherit autotools
