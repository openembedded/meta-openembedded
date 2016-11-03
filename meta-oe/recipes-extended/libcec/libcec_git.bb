SUMMARY = "USB CEC Adaptor communication Library"
HOMEPAGE = "http://libcec.pulse-eight.com/"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=e61fd86f9c947b430126181da2c6c715"

DEPENDS = "p8platform udev lockdev"

DEPENDS += "${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'libx11 libxrandr', '', d)}"

PV = "3.1.0+gitr${SRCPV}"

SRCREV = "6d68d21243aa92862592435e8396b4280ea46c3f"
SRC_URI = "git://github.com/Pulse-Eight/libcec.git"

S = "${WORKDIR}/git"

inherit cmake pkgconfig

# Disable python wrapper, it doesn't have crosscompiles
EXTRA_OECMAKE = "-DCMAKE_INSTALL_LIBDIR=${libdir} -DCMAKE_INSTALL_LIBDIR_NOARCH=${libdir} \
                 -DSKIP_PYTHON_WRAPPER=1"

# cec-client and xbmc need the .so present to work :(
FILES_${PN} += "${libdir}/*.so"
INSANE_SKIP_${PN} = "dev-so"

# Adapter shows up as a CDC-ACM device
RRECOMMENDS_${PN} = "kernel-module-cdc-acm"
