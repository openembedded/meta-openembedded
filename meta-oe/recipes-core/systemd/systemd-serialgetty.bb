DESCRIPTION = "Systemd serial config"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://${WORKDIR}/LICENSE;md5=751419260aa954499f7abaabaa882bbe"

PR = "r2"

SERIAL_CONSOLE ?= "115200 ttyS0"

SRC_URI = "file://LICENSE \
           file://serial-getty@.service"

def get_serial_console_value(d, index):
    c = d.getVar('SERIAL_CONSOLE', True)

    if len(c):
        return c.split()[index]

    return ""

def get_baudrate(d):
    return get_serial_console_value(d, 0)

def get_console(d):
    return get_serial_console_value(d, 1)

do_install() {
	if [ ! ${@get_baudrate(d)} = "" ]; then
		sed -i -e s/\@BAUDRATE\@/${@get_baudrate(d)}/g ${WORKDIR}/serial-getty@.service
		install -d ${D}${base_libdir}/systemd/system/
		install -d ${D}${sysconfdir}/systemd/system/getty.target.wants/
		install ${WORKDIR}/serial-getty@.service ${D}${base_libdir}/systemd/system/

		# enable the service
		ln -sf ${base_libdir}/systemd/system/serial-getty@.service \
			${D}${sysconfdir}/systemd/system/getty.target.wants/serial-getty@${@get_console(d)}.service
	fi
}

PACKAGES = "${PN} ${PN}-dbg ${PN}-dev ${PN}-doc"

RRECOMMENDS_${PN} = ""
RDEPENDS_${PN} = "systemd"

# This is a machine specific file
FILES_${PN} = "${base_libdir}/systemd/system/serial-getty@.service ${sysconfdir}"
PACKAGE_ARCH = "${MACHINE_ARCH}"
