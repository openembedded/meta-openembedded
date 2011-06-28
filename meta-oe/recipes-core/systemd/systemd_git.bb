DESCRIPTION = "Systemd a init replacement"
HOMEPAGE = "http://www.freedesktop.org/wiki/Software/systemd"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://LICENSE;md5=751419260aa954499f7abaabaa882bbe"

DEPENDS = "acl readline udev dbus libcap libcgroup"
DEPENDS += "${@base_contains('DISTRO_FEATURES', 'pam', 'libpam', '', d)}"

SERIAL_CONSOLE ?= "115200 /dev/ttyS0"

PRIORITY = "optional"
SECTION = "base/shell"

inherit gitpkgv
PKGV = "v${GITPKGVTAG}"

PV = "git"
PR = "r2"

inherit autotools vala

SRCREV = "ae556c210942cb6986c6d77b58505b5daa66bbe2"

SRC_URI = "git://anongit.freedesktop.org/systemd;protocol=git \
           file://0001-systemd-disable-xml-file-stuff-and-introspection.patch \
           file://serial-getty@.service \
          "

S = "${WORKDIR}/git"

SYSTEMDDISTRO ?= "debian"
SYSTEMDDISTRO_angstrom = "angstrom"

# The gtk+ tools should get built as a separate recipe e.g. systemd-tools
EXTRA_OECONF = " --with-distro=${SYSTEMDDISTRO} \
                 --with-rootdir=${base_prefix} \
                 ${@base_contains('DISTRO_FEATURES', 'pam', '--enable-pam', '--disable-pam', d)} \
                 --disable-gtk \
               "

def get_baudrate(bb, d):
    return bb.data.getVar('SERIAL_CONSOLE', d, 1).split()[0]

def get_console(bb, d):
    return bb.data.getVar('SERIAL_CONSOLE', d, 1).split()[1]

do_install() {
	autotools_do_install

	if [ ! ${@get_baudrate(bb, d)} = "" ]; then
		sed -i -e s/\@BAUDRATE\@/${@get_baudrate(bb, d)}/g ${WORKDIR}/serial-getty@.service
		install ${WORKDIR}/serial-getty@.service ${D}${base_libdir}/systemd/system/
		ln -sf ${base_libdir}/systemd/system/serial-getty@.service \
			${D}${sysconfdir}/systemd/system/getty.target.wants/serial-getty@${@get_console(bb, d)}.service
		fi
}

# ARM doesn't support hugepages, so don't try to mount them
do_install_append_arm() {
	rm -f ${D}${base_libdir}/systemd/system/*hugepages.mount
	rm -f ${D}${base_libdir}/systemd/system/*/*hugepages.mount
}

PACKAGES =+ "${PN}-gui ${PN}-serialgetty"

FILES_${PN}-gui = "${bindir}/systemadm"

# This is a machine specific file
FILES_${PN}-serialgetty = "${base_libdir}/systemd/system/serial-getty@.service ${sysconfdir}/systemd/system/getty.target.wants/getty@${@get_console(bb, d)}.service"
PACKAGE_ARCH_${PN}-serialgetty = "${MACHINE_ARCH}"

FILES_${PN} = " ${base_bindir}/* \
                ${datadir}/dbus-1/services \
                ${datadir}/dbus-1/system-services \
                ${datadir}/polkit-1 \
                ${datadir}/${PN} \
                ${sysconfdir} \
                ${base_libdir}/systemd/* \
                ${base_libdir}/systemd/system/* \
                ${base_libdir}/udev/rules.d \
                ${base_libdir}/security/*.so \
                /cgroup \
                ${bindir}/systemd* \
                ${libdir}/tmpfiles.d/*.conf \
                ${libdir}/systemd \
               "

FILES_${PN}-dbg += "${base_libdir}/systemd/.debug ${base_libdir}/systemd/*/.debug"

RDEPENDS_${PN} += "dbus-systemd udev-systemd"

# kbd -> loadkeys,setfont
# systemd calls 'modprobe -sab --', which busybox doesn't support due to lack 
# of blacklist support, so use proper modprobe from module-init-tools
# And pull in the kernel modules mentioned in INSTALL
RRECOMMENDS_${PN} += "kbd kbd-consolefonts \
                      ${PN}-serialgetty \
                      util-linux-agetty \
                      module-init-tools \
                      kernel-module-autofs4 kernel-module-unix kernel-module-ipv6 \
"

# TODO:
# u-a for runlevel and telinit

pkg_postinst_${PN} () {
update-alternatives --install ${base_sbindir}/init init ${base_bindir}/systemd 300
update-alternatives --install ${base_sbindir}/halt halt ${base_bindir}/systemctl 300
update-alternatives --install ${base_sbindir}/reboot reboot ${base_bindir}/systemctl 300
update-alternatives --install ${base_sbindir}/shutdown shutdown ${base_bindir}/systemctl 300
update-alternatives --install ${base_sbindir}/poweroff poweroff ${base_bindir}/systemctl 300

# can't do this offline, but we need the u-a bits above
if [ "x$D" != "x" ]; then
	echo "can't do addgroup offline" ; exit 1
else
	grep "^lock:" /etc/group > /dev/null || addgroup lock
fi
}

