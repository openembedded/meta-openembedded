DESCRIPTION = "Units to make systemd work better with existing sysvinit scripts"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58"

PR = "r15"

inherit allarch

SRC_URI = "file://*.service"

do_install() {
	install -d ${D}${base_libdir}/systemd/system/basic.target.wants
	install -d ${D}${base_libdir}/systemd/system/sysinit.target.wants/
	install -m 0644 ${WORKDIR}/run-postinsts.service ${D}${base_libdir}/systemd/system
	ln -sf ../run-postinsts.service ${D}${base_libdir}/systemd/system/basic.target.wants/
	ln -sf ../run-postinsts.service ${D}${base_libdir}/systemd/system/sysinit.target.wants/

	install -m 0644 ${WORKDIR}/machineid.service ${D}${base_libdir}/systemd/system
	ln -sf ../machineid.service ${D}${base_libdir}/systemd/system/sysinit.target.wants/
	ln -sf ../machineid.service ${D}${base_libdir}/systemd/system/basic.target.wants/

	# hack to make old style sysvinit postinsts succeed
	install -d ${D}${bindir}
	echo "echo 1" > ${D}${bindir}/runlevel
	chmod 0755 ${D}${bindir}/runlevel
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

FILES_${PN} = "${base_libdir}/systemd/system ${bindir}"
RDPEPENDS_${PN} = "systemd"


