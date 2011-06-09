NOTE = "This is just a dummy package to get packages stop complaining about gconftool-2 \
        which they are not using anyway... :D"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58"

PR = "r1"

inherit native

do_install() {
        install -d ${D}${bindir}
        echo "#!/bin/sh" >${D}${bindir}/gconftool-2
        chmod a+rx ${D}${bindir}/gconftool-2
}

NATIVE_INSTALL_WORKS = "1"
