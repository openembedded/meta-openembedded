DESCRIPTION = "udev is a daemon which dynamically creates and removes device nodes from \
/dev/, handles hotplug events and loads drivers at boot time. It replaces \
the hotplug package and requires a kernel not older than 2.6.27."

# udev 169 and up require kernel 2.6.36 for ARM: 
# http://git.kernel.org/?p=linux/hotplug/udev.git;a=commit;h=67a77c8bf299f6264f001677becd056316ebce2f

LICENSE = "GPLv2+ & LGPLv2.1+"
LICENSE_${PN} = "GPLv2+"
LICENSE_libudev = "LGPLv2.1+"
LICENSE_libgudev = "LGPLv2.1+"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe \
                    file://libudev/COPYING;md5=a6f89e2100d9b6cdffcea4f398e37343 \
                    file://extras/gudev/COPYING;md5=a6f89e2100d9b6cdffcea4f398e37343"

# Needed for udev-extras
DEPENDS = "gperf-native usbutils acl glib-2.0"

PR = "r4"

# version specific SRC_URI
SRC_URI = "${KERNELORG_MIRROR}/linux/utils/kernel/hotplug/udev-${PV}.tar.gz \
           file://0001-rip-put-doc-generation-it-depends-on-a-working-docto.patch \
           file://gtk-doc.make"

# generic SRC_URI
SRC_URI += " \
       file://touchscreen.rules \
       file://modprobe.rules \
       file://default \
       file://init \
       file://cache \
"

SRC_URI[md5sum] = "2afd20ee1c790eac6d7abe0498ebb414"
SRC_URI[sha256sum] = "0f753ad9c3022a074d1c052bcee9139581db825283771ac78c0be0d1de109d0c"

# Machine specific udev rules should be in their own recipe that ${PN} can add to RRECOMMENDS

inherit update-rc.d autotools

EXTRA_OECONF += " \
                  --disable-introspection \
                  --with-pci-ids-path=/usr/share/misc \
                  ac_cv_file__usr_share_pci_ids=no \
                  ac_cv_file__usr_share_hwdata_pci_ids=no \
                  ac_cv_file__usr_share_misc_pci_ids=yes \
                  --sbindir=${base_sbindir} \
                  --libexecdir=${base_libdir}/udev \
                  --with-rootlibdir=${base_libdir} \
                  --disable-gtk-doc-html \
                  --with-systemdsystemunitdir=${base_libdir}/systemd/system/ \
"

do_configure_prepend() {
	cp ${WORKDIR}/gtk-doc.make ${S}
}

INITSCRIPT_NAME = "udev"
INITSCRIPT_PARAMS = "start 04 S ."

PACKAGES =+ "${PN}-systemd libudev libgudev udev-utils udev-consolekit"

FILES_${PN}-systemd = "${base_libdir}/systemd"
RDEPENDS_${PN}-systemd += "udev"

FILES_libudev = "${base_libdir}/libudev.so.*"
FILES_libgudev = "${base_libdir}/libgudev*.so.*"

FILES_udev-utils = "${bindir}/udevinfo ${bindir}/udevtest ${base_sbindir}/udevadm"

RPROVIDES_${PN} = "hotplug"
FILES_${PN} += "${usrbindir}/* ${usrsbindir}/udevd"
FILES_${PN}-dbg += "${usrbindir}/.debug ${usrsbindir}/.debug"
RDEPENDS_${PN} += "module-init-tools-depmod udev-utils"
RRECOMMENDS_${PN} += "util-linux-blkid"

# udev installs binaries under $(udev_prefix)/lib/udev, even if ${libdir}
# is ${prefix}/lib64
FILES_${PN} += "/lib/udev*"
FILES_${PN}-dbg += "/lib/udev/.debug"
 
FILES_${PN}-consolekit += "${libdir}/ConsoleKit"
RDEPENDS_${PN}-consolekit += "${@base_contains('DISTRO_FEATURES', 'x11', 'consolekit', '', d)}"

do_install () {
	install -d ${D}${usrsbindir} \
		   ${D}${sbindir}
	oe_runmake 'DESTDIR=${D}' INSTALL=install install
	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/init ${D}${sysconfdir}/init.d/udev
	install -m 0755 ${WORKDIR}/cache ${D}${sysconfdir}/init.d/udev-cache

	install -d ${D}${sysconfdir}/default
	install -m 0755 ${WORKDIR}/default ${D}${sysconfdir}/default/udev

	install -m 0644 ${WORKDIR}/*.rules         ${D}${sysconfdir}/udev/rules.d/

	touch ${D}${sysconfdir}/udev/saved.uname
	touch ${D}${sysconfdir}/udev/saved.cmdline
	touch ${D}${sysconfdir}/udev/saved.devices
	touch ${D}${sysconfdir}/udev/saved.atags

	# disable udev-cache sysv script on systemd installs
	ln -sf /dev/null ${D}/${base_libdir}/systemd/system/udev-cache.service
}

# Create the cache after checkroot has run
pkg_postinst_udev_append() {
	if test "x$D" != "x"; then
		OPT="-r $D"
	else
		OPT="-s"
	fi
	update-rc.d $OPT udev-cache start 36 S .
}
