DESCRIPTION = "Wrapper to enable of systemd services"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58"

PR = "r2"

inherit native

SRC_URI = "file://systemctl"

do_install() {
	install -d ${D}${bindir}
	install -m 0755 ${WORKDIR}/systemctl ${D}${bindir}
}
