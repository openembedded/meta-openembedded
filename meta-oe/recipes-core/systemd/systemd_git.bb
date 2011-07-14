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
PR = "r0"

inherit autotools vala

SRCREV = "501c92c432c7bef452a2714c29519bdd45281a72"

SRC_URI = "git://anongit.freedesktop.org/systemd;protocol=git \
           file://0001-systemd-disable-xml-file-stuff-and-introspection.patch \
           file://use-nonet-for-docbook.patch \
           ${UCLIBCPATCHES} \
          "
UCLIBCPATCHES = ""
UCLIBCPATCHES_libc-uclibc = "file://paper-over-mkostemp.patch \
                             file://format-replace-m-uclibc.patch \
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

do_install() {
	autotools_do_install
	# provided by a seperate recipe
	rm ${D}${base_libdir}/systemd/system/serial-getty* -f
}

PACKAGES =+ "${PN}-gui"

FILES_${PN}-gui = "${bindir}/systemadm"

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
# swapon -p is also not supported by busybox
RRECOMMENDS_${PN} += "kbd kbd-consolefonts \
                      systemd-serialgetty \
                      util-linux-agetty \
                      util-linux-swaponoff \
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

