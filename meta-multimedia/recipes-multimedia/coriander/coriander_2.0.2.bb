SUMMARY = "Control and capture GUI for IIDC compliant cameras"
HOMEPAGE = "http://damien.douxchamps.net/ieee1394/coriander/"
SECTION = "applications"
LICENSE = "GPL-3.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"
DEPENDS = "gtk+ libgnomeui libraw1394 libdc1394 libxv tiff"

SRC_URI = "${SOURCEFORGE_MIRROR}/project/${PN}/coriander-2/${PV}/${P}.tar.gz \
           file://cross-compile.patch \
          "

SRC_URI[md5sum] = "431d98fb013217681f97ade168201fb8"
SRC_URI[sha256sum] = "5c7fd31cb58d398e2742352bf1ffbd2ca22e06686c6668ecfd437735c2b79123"

inherit autotools gettext
