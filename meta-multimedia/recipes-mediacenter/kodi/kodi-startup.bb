SUMMARY = "Systemd service for kodi startup"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690"

PV = "1.0"

SRC_URI = "file://kodi.service"

inherit systemd

do_install() {
	install -d ${D}/lib/systemd/system
	install -m 0644 ${WORKDIR}/kodi.service ${D}/lib/systemd/system/ 
}

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "kodi.service"

RDEPENDS_${PN} += "xinit kodi"
