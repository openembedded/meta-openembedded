SUMMARY = "System-wide Performance Profiler for Linux"
HOMEPAGE = "http://www.sysprof.com"
LICENSE = "GPLv3+"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504 \
                    file://src/sysprof/sysprof-application.c;endline=17;md5=a3de8df3b0f8876dd01e1388d2d4b607"

GNOMEBASEBUILDCLASS = "meson"
inherit gnomebase gnome-help gettext systemd upstream-version-is-even gsettings mime mime-xdg

DEPENDS += " \
    glib-2.0-native \
    yelp-tools-native \
    libxml2-native \
    glib-2.0 \
"

SRC_URI[archive.sha256sum] = "3c16e03efdfe5d3e71c4d239c67741ad37f72ce8971e2f7faf168dbd5e93e886"

PACKAGECONFIG ?= "${@bb.utils.contains('DISTRO_FEATURES', 'polkit', 'sysprofd', '', d)} \
                  ${@bb.utils.contains('DISTRO_FEATURES', 'polkit', 'libsysprof', '', d)} \
                  ${@bb.utils.contains_any('DISTRO_FEATURES', '${GTK3DISTROFEATURES}', 'gtk', '', d)}"
PACKAGECONFIG[gtk] = "-Denable_gtk=true,-Denable_gtk=false,gtk+3 libdazzle"
PACKAGECONFIG[sysprofd] = "-Dwith_sysprofd=bundled,-Dwith_sysprofd=none,polkit"
PACKAGECONFIG[libsysprof] = "-Dlibsysprof=true,-Dlibsysprof=false,polkit"

EXTRA_OEMESON += "-Dsystemdunitdir=${systemd_unitdir}/system"

SOLIBS = ".so"
FILES_SOLIBSDEV = ""

SYSTEMD_SERVICE:${PN} = "${@bb.utils.contains('PACKAGECONFIG', 'sysprofd', 'sysprof2.service sysprof3.service', '', d)}"

FILES:${PN} += " \
    ${datadir}/dbus-1/system-services \
    ${datadir}/dbus-1/system.d \
    ${datadir}/dbus-1/interfaces \
    ${datadir}/metainfo \
"
