DESCRIPTION = "Systemd a init replacement"
HOMEPAGE = "http://www.freedesktop.org/wiki/Software/systemd"

LICENSE = "GPLv2 & LGPLv2.1 & MIT"
LIC_FILES_CHKSUM = "file://LICENSE.GPL2;md5=751419260aa954499f7abaabaa882bbe \
                    file://LICENSE.LGPL2.1;md5=fb919cc88dbe06ec0b0bd50e001ccf1f \
                    file://LICENSE.MIT;md5=544799d0b492f119fa04641d1b8868ed"

PROVIDES = "udev"

DEPENDS = "xz kmod docbook-sgml-dtd-4.1-native intltool-native gperf-native acl readline dbus libcap libcgroup tcp-wrappers usbutils glib-2.0"
DEPENDS += "${@base_contains('DISTRO_FEATURES', 'pam', 'libpam', '', d)}"

SERIAL_CONSOLE ?= "115200 /dev/ttyS0"

SECTION = "base/shell"

inherit gitpkgv
PKGV = "v${GITPKGVTAG}"

PV = "git"
PR = "r4"

inherit useradd pkgconfig autotools vala perlnative

SRCREV = "cd96b3b86abb4a88cac2722bdfb6e5d4413f6831"

SRC_URI = "git://anongit.freedesktop.org/systemd/systemd;protocol=git \
           file://gtk-doc.make \
           file://touchscreen.rules \
           file://modprobe.rules \
           file://var-run.conf \
          "
LDFLAGS_libc-uclibc_append = " -lrt"

S = "${WORKDIR}/git"

SYSTEMDDISTRO ?= "debian"
SYSTEMDDISTRO_angstrom = "angstrom"

# The gtk+ tools should get built as a separate recipe e.g. systemd-tools
EXTRA_OECONF = " --with-distro=${SYSTEMDDISTRO} \
                 --with-rootprefix=${base_prefix} \
                 --with-rootlibdir=${base_libdir} \
                 --sbindir=${base_sbindir} \
                 --libexecdir=${base_libdir} \
                 ${@base_contains('DISTRO_FEATURES', 'pam', '--enable-pam', '--disable-pam', d)} \
                 --enable-xz \
                 --disable-manpages \
                 --disable-coredump \
                 --disable-introspection \
                 --with-pci-ids-path=/usr/share/misc \
                 ac_cv_file__usr_share_pci_ids=no \
                 ac_cv_file__usr_share_hwdata_pci_ids=no \
                 ac_cv_file__usr_share_misc_pci_ids=yes \
                 --disable-gtk-doc-html \ 
                 --disable-tcpwrap \
               "

# There's no docbook-xsl-native, so for the xsltproc check to false
do_configure_prepend() {
	sed -i /xsltproc/d configure.ac

	cp ${WORKDIR}/gtk-doc.make ${S}/docs/

	# we only have /home/root, not /root
	sed -i -e 's:=/root:=/home/root:g' units/*.service*
}

do_install() {
	autotools_do_install
	# provided by a seperate recipe
	rm ${D}${systemd_unitdir}/system/serial-getty* -f

	# provide support for initramfs
	ln -s ${systemd_unitdir}/systemd ${D}/init

	# create dir for journal
	install -d ${D}${localstatedir}/log/journal

	# create machine-id
	# 20:12 < mezcalero> koen: you have three options: a) run systemd-machine-id-setup at install time, b) have / read-only and an empty file there (for stateless) and c) boot with / writable
	touch ${D}${sysconfdir}/machine-id

	install -m 0644 ${WORKDIR}/*.rules ${D}${sysconfdir}/udev/rules.d/

	install -m 0644 ${WORKDIR}/var-run.conf ${D}${sysconfdir}/tmpfiles.d/
}

python populate_packages_prepend (){
    systemdlibdir = d.getVar("base_libdir", True)
    do_split_packages(d, systemdlibdir, '^lib(.*)\.so\.*', 'lib%s', 'Systemd %s library', extra_depends='', allow_links=True)
}

PACKAGES =+ "${PN}-gui ${PN}-vconsole-setup ${PN}-initramfs ${PN}-analyze"

USERADD_PACKAGES = "${PN}"
GROUPADD_PARAM_${PN} = "-r lock"

FILES_${PN}-analyze = "${bindir}/systemd-analyze"
RDEPENDS_${PN}-analyze = "python-dbus"
RRECOMMENDS_${PN}-analyze = "python-pycairo"

FILES_${PN}-initramfs = "/init"
RDEPENDS_${PN}-initramfs = "${PN}"

FILES_${PN}-gui = "${bindir}/systemadm"

FILES_${PN}-vconsole-setup = "${systemd_unitdir}/systemd-vconsole-setup \
                              ${systemd_unitdir}/system/systemd-vconsole-setup.service \
                              ${systemd_unitdir}/system/sysinit.target.wants/systemd-vconsole-setup.service"

RRECOMMENDS_${PN}-vconsole-setup = "kbd kbd-consolefonts"

FILES_${PN} = " ${base_bindir}/* \
                ${datadir}/dbus-1/services \
                ${datadir}/dbus-1/system-services \
                ${datadir}/polkit-1 \
                ${datadir}/${PN} \
                ${sysconfdir}/bash_completion.d/ \
                ${sysconfdir}/binfmt.d/ \
                ${sysconfdir}/dbus-1/ \
                ${sysconfdir}/machine-id \
                ${sysconfdir}/modules-load.d/ \
                ${sysconfdir}/sysctl.d/ \
                ${sysconfdir}/systemd/ \
                ${sysconfdir}/tmpfiles.d/ \
                ${sysconfdir}/xdg/ \
                ${systemd_unitdir}/* \
                ${systemd_unitdir}/system/* \
                ${base_libdir}/udev/rules.d/99-systemd.rules \
                ${base_libdir}/security/*.so \
                /cgroup \
                ${bindir}/systemd* \
                ${libdir}/tmpfiles.d/*.conf \
                ${libdir}/systemd \
                ${libdir}/binfmt.d \
                ${libdir}/modules-load.d \
                ${libdir}/sysctl.d \
                ${localstatedir} \
                ${libexecdir} \
                ${base_libdir}/udev/rules.d/70-uaccess.rules \
                ${base_libdir}/udev/rules.d/71-seat.rules \
                ${base_libdir}/udev/rules.d/73-seat-late.rules \
                ${base_libdir}/udev/rules.d/99-systemd.rules \
               "

FILES_${PN}-dbg += "${systemd_unitdir}/.debug ${systemd_unitdir}/*/.debug ${base_libdir}/security/.debug/"
FILES_${PN}-dev += "${base_libdir}/security/*.la ${datadir}/dbus-1/interfaces/ ${sysconfdir}/rpm/macros.systemd"

RDEPENDS_${PN} += "dbus-systemd udev-systemd"

# kbd -> loadkeys,setfont
# systemd calls 'modprobe -sab --', which busybox doesn't support due to lack 
# of blacklist support, so use proper modprobe from module-init-tools
# And pull in the kernel modules mentioned in INSTALL
# swapon -p is also not supported by busybox
# busybox mount is broken
RRECOMMENDS_${PN} += "systemd-serialgetty \
                      util-linux-agetty \
                      util-linux-swaponoff \
                      util-linux-fsck e2fsprogs-e2fsck \
                      module-init-tools \
                      util-linux-mount util-linux-umount \
                      kernel-module-autofs4 kernel-module-unix kernel-module-ipv6 \
"

PACKAGES =+ "udev-dbg udev udev-consolekit udev-utils udev-systemd"

FILES_udev-dbg += "${base_libdir}/udev/.debug"

RDEPENDS_udev += "udev-utils"
RPROVIDES_udev = "hotplug"

FILES_udev += "${base_libdir}/udev/udevd \
               ${base_libdir}/systemd/systemd-udevd \
               ${base_libdir}/udev/accelerometer \
               ${base_libdir}/udev/ata_id \
               ${base_libdir}/udev/cdrom_id \
               ${base_libdir}/udev/collect \
               ${base_libdir}/udev/findkeyboards \
               ${base_libdir}/udev/keyboard-force-release.sh \
               ${base_libdir}/udev/keymap \
               ${base_libdir}/udev/mtd_probe \
               ${base_libdir}/udev/scsi_id \
               ${base_libdir}/udev/v4l_id \
               ${base_libdir}/udev/keymaps \
               ${base_libdir}/udev/rules.d/4*.rules \
               ${base_libdir}/udev/rules.d/5*.rules \
               ${base_libdir}/udev/rules.d/6*.rules \
               ${base_libdir}/udev/rules.d/70-power-switch.rules \
               ${base_libdir}/udev/rules.d/75*.rules \
               ${base_libdir}/udev/rules.d/78*.rules \
               ${base_libdir}/udev/rules.d/8*.rules \
               ${base_libdir}/udev/rules.d/95*.rules \
               ${sysconfdir}/udev \
              "

FILES_udev-consolekit += "${libdir}/ConsoleKit"
RDEPENDS_udev-consolekit += "${@base_contains('DISTRO_FEATURES', 'x11', 'consolekit', '', d)}"

FILES_udev-utils = "${bindir}/udevadm"

FILES_udev-systemd = "${base_libdir}/systemd/system/*udev* ${base_libdir}/systemd/system/*.wants/*udev*"
RDEPENDS_udev-systemd = "udev"

# TODO:
# u-a for runlevel and telinit

pkg_postinst_systemd () {
update-alternatives --install ${base_sbindir}/init init ${systemd_unitdir}/systemd 300
update-alternatives --install ${base_sbindir}/halt halt ${base_bindir}/systemctl 300
update-alternatives --install ${base_sbindir}/reboot reboot ${base_bindir}/systemctl 300
update-alternatives --install ${base_sbindir}/shutdown shutdown ${base_bindir}/systemctl 300
update-alternatives --install ${base_sbindir}/poweroff poweroff ${base_bindir}/systemctl 300
}

pkg_prerm_systemd () {
update-alternatives --remove init ${systemd_unitdir}/systemd
update-alternatives --remove halt ${base_bindir}/systemctl
update-alternatives --remove reboot ${base_bindir}/systemctl
update-alternatives --remove shutdown ${base_bindir}/systemctl
update-alternatives --remove poweroff ${base_bindir}/systemctl
}
