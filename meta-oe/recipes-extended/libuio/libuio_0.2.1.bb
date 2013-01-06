DESCRIPTION = "Libuio - helper library for UIO subsystem."
SECTION = "base"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=393a5ca445f6965873eca0259a17f833"

SRC_URI = "git://libuio.git.sourceforge.net/gitroot/libuio/libuio"

inherit autotools

SRCREV = "e1e0e4fa28838b3115e215c9553905fe630ca145"

S = "${WORKDIR}/git"

PACKAGES += "${PN}-tools"

FILES_${PN} = "${libdir}"
FILES_${PN}-tools = "${bindir}"
