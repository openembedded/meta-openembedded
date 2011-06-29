require systemd_git.bb

SRC_URI = "git://anongit.freedesktop.org/systemd;protocol=git \
           file://serial-getty@.service \
          "

DESCRIPTION = "Systemd serial config"
SERIAL_CONSOLE ?= "115200 /dev/ttyS0"

do_configure() {
	:
}

do_compile() {
	:
}

def get_baudrate(bb, d):
    return bb.data.getVar('SERIAL_CONSOLE', d, 1).split()[0]

def get_console(bb, d):
    return bb.data.getVar('SERIAL_CONSOLE', d, 1).split()[1]

do_install() {
	if [ ! ${@get_baudrate(bb, d)} = "" ]; then
		sed -i -e s/\@BAUDRATE\@/${@get_baudrate(bb, d)}/g ${WORKDIR}/serial-getty@.service
		install -d ${D}${base_libdir}/systemd/system/
		install -d ${D}${sysconfdir}/systemd/system/getty.target.wants/
		install ${WORKDIR}/serial-getty@.service ${D}${base_libdir}/systemd/system/
		ln -sf ${base_libdir}/systemd/system/serial-getty@.service \
			${D}${sysconfdir}/systemd/system/getty.target.wants/serial-getty@${@get_console(bb, d)}.service
		fi
}

PACKAGES = "${PN} ${PN}-dbg ${PN}-dev ${PN}-doc"

RRECOMMENDS_${PN} = ""
RDEPENDS_${PN} = "systemd"

# This is a machine specific file
FILES_${PN} = "${base_libdir}/systemd/system/serial-getty@.service ${sysconfdir}"
PACKAGE_ARCH = "${MACHINE_ARCH}"

