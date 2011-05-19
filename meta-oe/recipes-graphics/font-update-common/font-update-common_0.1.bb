DESCRIPTION = "Scripts to be called when fonts are installed or removed \
to make them known to the WM, whether X11 or Opie"
AUTHOR = "Rolf Leggewie <oe-devel@rolf.leggewie.biz"

PR = "r1"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58"

SRC_URI = "file://update-fonts"

do_install() {
        install -d ${D}${bindir}
        install -d ${D}${sysconfdir}/update-fonts-common.d/
        install -m 0755 ${WORKDIR}/update-fonts ${D}${bindir}
}

inherit allarch
