DESCRIPTION = "Socket based activation for dropbear ssh server"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58"

inherit allarch

SRC_URI = "file://dropbearkey.service \
           file://dropbear@.service \
           file://dropbear.socket \
          "

do_configure() {
	:
}

do_compile() {
	:
}

do_install() {
	install -d ${D}${base_libdir}/systemd/system
	install -m 0644 ${WORKDIR}/dropbear*.* ${D}${base_libdir}/systemd/system/
}

pkg_postinst_${PN} () {
if test "x$D" != "x"; then
	exit 1
fi
systemctl enable dropbear.socket
ln -sf /dev/null ${base_libdir}/systemd/system/dropbear.service
}

pkg_postrm_${PN} () {
systemctl disable dropbear.socket
rm -f ${base_libdir}/systemd/system/dropbear.service
}

RDEPENDS_${PN} = "systemd dropbear"
FILES_${PN} = "${base_libdir}/systemd/system"

