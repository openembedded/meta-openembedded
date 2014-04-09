SUMMARY = "PolicyKit Authorization Framework"
DESCRIPTION = "The polkit package is an application-level toolkit for defining and handling the policy that allows unprivileged processes to speak to privileged processes."
HOMEPAGE = "http://www.freedesktop.org/wiki/Software/polkit"
LICENSE = "LGPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=155db86cdbafa7532b41f390409283eb \
                    file://src/polkit/polkit.h;beginline=1;endline=20;md5=0a8630b0133176d0504c87a0ded39db4"

DEPENDS = "expat glib-2.0 intltool-native gobject-introspection-stub mozjs"

inherit autotools gtk-doc pkgconfig useradd systemd

PACKAGECONFIG = "${@base_contains('DISTRO_FEATURES', 'pam', 'pam', '', d)} \
                 ${@base_contains('DISTRO_FEATURES','systemd','systemd','consolekit',d)}"

PACKAGECONFIG[pam] = "--with-authfw=pam,--with-authfw=shadow,libpam,libpam"
PACKAGECONFIG[systemd] = "--enable-libsystemd-login=yes --with-systemdsystemunitdir=${systemd_unitdir}/system/,--enable-libsystemd-login=no --with-systemdsystemunitdir=,systemd"
# there is no --enable/--disable option for consolekit and it's not picked by shlibs, so add it to RDEPENDS
PACKAGECONFIG[consolekit] = ",,,consolekit"

PAM_SRC_URI = "file://polkit-1_pam.patch"
SRC_URI = "http://www.freedesktop.org/software/polkit/releases/polkit-${PV}.tar.gz \
           ${@base_contains('DISTRO_FEATURES', 'pam', '${PAM_SRC_URI}', '', d)} \
           file://0001-configure.ac-Check-only-for-libsystemd-not-libsystem.patch \
"

SRC_URI[md5sum] = "b0f2fa00a55f47c6a5d88e9b73f80127"
SRC_URI[sha256sum] = "d695f43cba4748a822fbe864dd32c4887c5da1c71694a47693ace5e88fcf6af6"

EXTRA_OECONF = "--with-os-type=moblin --disable-man-pages --disable-introspection"

PACKAGES =+ "${PN}-examples"

FILES_${PN} += " \
    ${libdir}/${BPN}-1 \
    ${datadir}/dbus-1 \
    ${datadir}/${BPN}-1 \
"
FILES_${PN}-dbg += "${libdir}/${BPN}-1/.debug"

FILES_${PN}-examples = "${bindir}/*example*"

USERADD_PACKAGES = "${PN}"
USERADD_PARAM_${PN} = "--system --no-create-home --user-group --home-dir ${sysconfdir}/${BPN}-1 polkitd"

SYSTEMD_SERVICE_${PN} = "${BPN}.service"
SYSTEMD_AUTO_ENABLE = "disable"
