DESCRIPTION = "Scripts to be called when fonts are installed or removed \
to make them known to the WM, whether X11 or Opie"
AUTHOR = "Rolf Leggewie <oe-devel@rolf.leggewie.biz"

LICENSE = "MIT"

SRC_URI = "file://update-fonts"

do_install() {
        install -d ${D}${bindir}
        install -d ${D}${sysconfdir}/update-fonts-common.d/
        install -m 0755 ${WORKDIR}/update-fonts ${D}${bindir}
}

PACKAGE_ARCH = "all"
