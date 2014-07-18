require lcdproc5.inc

SRC_URI += "file://automake-111-fix.patch"

SRC_URI[md5sum] = "c92d4529d36eeec6d9d6fd0a4aa3ec82"
SRC_URI[sha256sum] = "b136b47d7bf585a003334f432d8730a36ef5ed1cd520084b919667d825e48d42"

PACKAGECONFIG ?= ""
PACKAGECONFIG[g15] = ",,libg15 g15daemon libg15render,"
PACKAGECONFIG[hid] = "--enable-libhid,--disable-libhid,libhid"

LCD_DRIVERS_append = "${@base_contains('PACKAGECONFIG', 'g15', '', ',!g15', d)}"

do_install_append () {
    # binaries
    install -D -m 0755 clients/lcdvc/lcdvc ${D}${sbindir}/lcdvc

    # configuration files
    install -D -m 0644 ${S}/clients/lcdvc/lcdvc.conf ${D}${sysconfdir}/lcdvc.conf
}

PACKAGES =+ "lcdvc"
CONFFILES_lcdvc = "${sysconfdir}/lcdvc.conf"
FILES_lcdvc = "${sysconfdir}/lcdvc.conf ${sbindir}/lcdvc"

