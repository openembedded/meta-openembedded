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

SRC_URI[md5sum] = "1e6119b4c12f2539ae5d191659652a89"
SRC_URI[sha256sum] = "1b4b38943bfc564ddd18d4314a6a12af5ce656b551e69154e818ad14231050b8"
