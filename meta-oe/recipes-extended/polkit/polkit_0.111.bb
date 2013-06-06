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
"

SRC_URI[md5sum] = "81b116edf986d8e13502929a171f4e0d"
SRC_URI[sha256sum] = "02ae544547211b687818c97bcbf19bf6b8b5be7fda93000525a8765c7bed1ea1"

EXTRA_OECONF = "--with-os-type=moblin --disable-man-pages --disable-introspection"

do_install_append() {
    # see configure.log for more details
    chown root:root ${D}${libdir}/${BPN}-1/polkit-agent-helper-1
    chmod 4755 ${D}${libdir}/${BPN}-1/polkit-agent-helper-1

    chown root:root ${D}${bindir}/pkexec
    chmod 4755 ${D}${bindir}/pkexec

    chown polkitd:polkitd ${D}${sysconfdir}/${BPN}-1/rules.d
    chmod 700 ${D}${sysconfdir}/${BPN}-1/rules.d

    chown polkitd:polkitd ${D}${datadir}/${BPN}-1/rules.d
    chmod 700 ${D}${datadir}/${BPN}-1/rules.d
}

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
