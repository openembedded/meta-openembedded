SUMMARY = "Lightweight and minimal (~20K) dumb-terminal emulation program"
SECTION = "console/utils"
LICENSE = "GPLv2+"
HOMEPAGE = "http://code.google.com/p/picocom/"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=393a5ca445f6965873eca0259a17f833"

SRC_URI = "http://picocom.googlecode.com/files/picocom-1.6.tar.gz"

CPPFLAGS_append = '-DVERSION_STR=\\"${PV}\\" -DUUCP_LOCK_DIR=\\"/var/lock\\" -DHIGH_BAUD'

do_install () {
    install -d ${D}${bindir}
    install -m 0755 ${PN} pcasc pcxm pcym pczm ${D}${bindir}/
}

SRC_URI[md5sum] = "426c3d30b82cbc80b0dafdccd6020c6c"
SRC_URI[sha256sum] = "df5774072de805ff06c6b1420dbcc932b1b00e919b49e22a7be14bcad5a0b3a1"
