DESCRIPTION = "Units to make systemd work better with existing sysvinit scripts"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58"

PR = "r16"

inherit allarch

SRC_URI = "file://*.service"

do_install() {
	install -d ${D}${systemd_unitdir}/system/basic.target.wants
	install -d ${D}${systemd_unitdir}/system/sysinit.target.wants/
	install -m 0644 ${WORKDIR}/run-postinsts.service ${D}${systemd_unitdir}/system
	ln -sf ../run-postinsts.service ${D}${systemd_unitdir}/system/basic.target.wants/
	ln -sf ../run-postinsts.service ${D}${systemd_unitdir}/system/sysinit.target.wants/

	install -m 0644 ${WORKDIR}/machineid.service ${D}${systemd_unitdir}/system
	ln -sf ../machineid.service ${D}${systemd_unitdir}/system/sysinit.target.wants/
	ln -sf ../machineid.service ${D}${systemd_unitdir}/system/basic.target.wants/

	# hack to make old style sysvinit postinsts succeed
	install -d ${D}${bindir}
	echo "echo 1" > ${D}${bindir}/runlevel
	chmod 0755 ${D}${bindir}/runlevel
}

SYSTEMD_DISABLED_SYSV_SERVICES = " \
  busybox-udhcpc \
  dnsmasq \
  hwclock \
  networking \
  syslog \
  syslog.busybox \
"

pkg_postinst_${PN} () {
cd $D${sysconfdir}/init.d

echo -n "Disabling the following sysv scripts: "

for i in ${SYSTEMD_DISABLED_SYSV_SERVICES} ; do
    if [ \( -e $i -o $i.sh \) -a ! -e $D${base_libdir}/systemd/system/$i.service ] ; then
        echo -n "$i " ; ln -s /dev/null $D${base_libdir}/systemd/system/$i.service
    fi
done ; echo
}

FILES_${PN} = "${systemd_unitdir}/system ${bindir}"
RDPEPENDS_${PN} = "systemd"


