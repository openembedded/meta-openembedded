DESCRIPTION = "Common X11 scripts and support files"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"

PR = "r3"

# we are using a gpe-style Makefile
inherit gpe

SRC_URI[md5sum] = "82f2f84cd96610e8f7b92c700cd31c14"
SRC_URI[sha256sum] = "cd04c33418f776b1e13fcc7af3d6bd0c7cccd03fbabd7dbcd97f88166cc34210"

SRC_URI_append = " \
                   file://gplv2-license.patch \
                   file://setDPI.sh \
                   file://89xdgautostart.sh \
                   file://89xTs_Calibrate.xinput_calibrator.patch \
                   file://90xXWindowManager.patch \
                   file://Xserver.add.xserver-system.patch \
                   file://Xserver.add.nocursor.for.gta.patch \
                   file://Xserver.add.dpi.for.gta.patch \
                   file://Xserver.n900.patch \
"


do_install_append() {
        install -m 0755 "${WORKDIR}/setDPI.sh" "${D}/etc/X11/Xinit.d/50setdpi"
        install -m 0755 "${WORKDIR}/89xdgautostart.sh" "${D}/etc/X11/Xsession.d/89xdgautostart"
        sed -i 's:^BINDIR=.*$:BINDIR=${bindir}:' ${D}/etc/X11/xserver-common
}

inherit allarch

RDEPENDS_${PN} = "xmodmap xrandr xdpyinfo fbset xinput-calibrator"

RCONFLICTS_${PN} = "xserver-kdrive-common x11-common"
RREPLACES_${PN} = "xserver-kdrive-common x11-common"

