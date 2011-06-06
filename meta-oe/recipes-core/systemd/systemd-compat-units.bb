DESCRIPTION = "Units to make systemd work better with existing sysvinit scripts"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58"

PR = "r1"

inherit allarch

SRC_URI = "file://opkg.service"

do_install() {
	install -d ${D}${base_libdir}/systemd/system/basic.target.wants
	install -m 0644 ${WORKDIR}/opkg.service ${D}${base_libdir}/systemd/system
	ln -sf ../opkg.service ${D}${base_libdir}/systemd/system/basic.target.wants/
}

pkg_postinst_${PN} () {
cd $D${sysconfdir}/init.d

echo -n "Disabling the following sysv scripts: "

for i in busybox-udhcpc dnsmasq hwclock.sh networking syslog syslog.busybox ; do
	if [ -e $i ] ; then
		echo -n "$i " ; ln -s /dev/null $D${base_libdir}/systemd/system/$i.service
	fi
done ; echo
}

FILES_${PN} = "${base_libdir}/systemd/system"
RDPEPENDS_${PN} = "systemd"


