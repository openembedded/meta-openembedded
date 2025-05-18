SUMMARY = "PolicyKit Authorization Framework"
DESCRIPTION = "The polkit package is an application-level toolkit for defining and handling the policy that allows unprivileged processes to speak to privileged processes."
HOMEPAGE = "http://www.freedesktop.org/wiki/Software/polkit"
LICENSE = "LGPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=155db86cdbafa7532b41f390409283eb"

SRC_URI = "git://github.com/freedesktop/polkit.git;protocol=https;branch=master"

S = "${WORKDIR}/git"
SRCREV = "da87c5698019897dd731bb2cbb54ebd9c9481f52"

DEPENDS = "expat glib-2.0"

inherit meson pkgconfig useradd systemd gettext gobject-introspection features_check

REQUIRED_DISTRO_FEATURES = "polkit"

PACKAGECONFIG = " \
	${@bb.utils.filter('DISTRO_FEATURES', 'pam', d)} \
	${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'systemd', 'consolekit', d)} \
	dbus \
	mozjs \
"
PACKAGECONFIG[dbus] = ",,dbus"
PACKAGECONFIG[gtk-doc] = "-Dgtk_doc=true,-Dgtk_doc=false,gtk-doc-native"
PACKAGECONFIG[pam] = "-Dauthfw=pam,-Dauthfw=shadow,libpam,libpam"
PACKAGECONFIG[systemd] = "-Dsession_tracking=libsystemd-login,-Dsession_tracking=ConsoleKit,systemd"
PACKAGECONFIG[consolekit] = ",,,consolekit"

# Default to mozjs javascript library
PACKAGECONFIG[mozjs] = "-Djs_engine=mozjs,,mozjs-102,,,duktape"
# duktape javascript engine is much smaller and faster but is not compatible with
# same javascript standards as mozjs. For example array.includes() function is not
# supported. Test rule compatibility when switching to duktape.
PACKAGECONFIG[duktape] = "-Djs_engine=duktape,,duktape,,,mozjs"

USERADD_PACKAGES = "${PN}"
USERADD_PARAM:${PN} = "--system --no-create-home --user-group --home-dir ${sysconfdir}/${BPN}-1 --shell /bin/nologin polkitd"

SYSTEMD_SERVICE:${PN} = "${BPN}.service"
SYSTEMD_AUTO_ENABLE = "disable"

do_install:append() {
	#Fix up permissions on polkit rules.d to work with rpm4 constraints
	chmod 700 ${D}/${datadir}/polkit-1/rules.d
	chown polkitd:root ${D}/${datadir}/polkit-1/rules.d
}

FILES:${PN} += "${libdir}/polkit-1 ${nonarch_libdir}/polkit-1 ${datadir}"
