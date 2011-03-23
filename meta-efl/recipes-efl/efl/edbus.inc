DESCRIPTION = "DBus and HAL convenience wrappers for EFL"
DEPENDS = "dbus ecore"
LICENSE = "MIT BSD"

inherit efl

BBCLASSEXTEND = "native"
INC_PR = "r0"

SRCNAME = "e_dbus"

EXTRA_OECONF = "--enable-build-test-gui"

PACKAGES =+ "${PN}-enotify ${PN}-edbus ${PN}-enm ${PN}-ehal ${PN}-econnman ${PN}-eofono ${PN}-eukit ${PN}-ebluez ${PN}-test-gui"
FILES_${PN}-enotify = "${libdir}/libenotify.so.* ${bindir}/e-notify-send"
FILES_${PN}-edbus = "${libdir}/libedbus.so.*"
FILES_${PN}-enm = "${libdir}/libenm.so.*"
FILES_${PN}-ehal = "${libdir}/libehal.so.*"
FILES_${PN}-econnman = "${libdir}/libeconnman.so.*"
FILES_${PN}-eofono = "${libdir}/libeofono.so.*"
FILES_${PN}-eukit = "${libdir}/libeukit.so.*"
FILES_${PN}-ebluez = "${libdir}/libebluez.so.*"
FILES_${PN}-test-gui = "${bindir}/e_dbus_hal"

RREPLACES_${PN}-enotify = "libenotify1"
RREPLACES_${PN}-edbus = "edbus"
RREPLACES_${PN}-enm = "edbus"
RREPLACES_${PN}-ehal = "edbus"
RREPLACES_${PN}-econnman = "edbus"
RREPLACES_${PN}-eofono = "edbus"
RREPLACES_${PN}-eukit = "edbus"
RREPLACES_${PN}-ebluez = "edbus"

