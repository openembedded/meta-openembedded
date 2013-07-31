DESCRIPTION = "Eeze is a library to simplify the use of devices"
LICENSE = "MIT BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=315521fe061b6fd4290ef01db714a3c8"
DEPENDS = "ecore eet udev"

inherit efl

BBCLASSEXTEND = "native"

# Some upgrade path tweaking
AUTO_LIBNAME_PKGS = ""

FILES_${PN} += "${libdir}/enlightenment/utils/eeze_scanner"
FILES_${PN}-dbg += "${libdir}/enlightenment/utils/.debug"

RRECOMMENDS_${PN} += "eject"

SRC_URI = "\
    ${E_MIRROR}/${SRCNAME}-${SRCVER}.tar.gz \
"

SRC_URI[md5sum] = "3ee68667f65860a0bdb7126e555155ff"
SRC_URI[sha256sum] = "9a4df9d086575c54d0116164d46b2c9e29362cc32398ea606c2365cbdf4157dd"
