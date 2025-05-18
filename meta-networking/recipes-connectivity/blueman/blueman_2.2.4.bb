DESCRIPTION = "Blueman is a GTK+ Bluetooth Manager"
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

DEPENDS = "gtk+3 glib-2.0 bluez5 python3-pygobject python3-cython-native"

inherit meson gettext systemd gsettings pkgconfig python3native gtk-icon-cache

SRC_URI = " \
    https://github.com/blueman-project/blueman/releases/download/${PV}/blueman-${PV}.tar.xz \
    file://0001-Search-for-cython3.patch \
    file://0002-fix-fail-to-enable-bluetooth.patch \
    file://0001-meson-add-pythoninstalldir-option.patch \
"
SRC_URI[sha256sum] = "55d639feeda0b43b18a659e65985213a54b47dcb1348f3b4effb5238db242602"

EXTRA_OEMESON = "-Druntime_deps_check=false -Dappindicator=false -Dpythoninstalldir=${@noprefix('PYTHON_SITEPACKAGES_DIR', d)}"

SYSTEMD_SERVICE:${PN} = "${BPN}-mechanism.service"
SYSTEMD_AUTO_ENABLE:${PN} = "disable"

RRECOMENDS_${PN} += "adwaita-icon-theme"
RDEPENDS:${PN} += " \
    python3-core \
    python3-dbus \
    python3-pygobject \
    python3-terminal \
    packagegroup-tools-bluetooth \
"

PACKAGECONFIG ??= " \
    ${@bb.utils.filter('DISTRO_FEATURES', 'polkit pulseaudio ', d)} \
    thunar \
"
PACKAGECONFIG[thunar] = "-Dthunar-sendto=true,-Dthunar-sendto=false"
PACKAGECONFIG[pulseaudio] = "-Dpulseaudio=true,-Dpulseaudio=false"
PACKAGECONFIG[polkit] = "-Dpolicykit=true,-Dpolicykit=false"

FILES:${PN} += " \
    ${datadir} \
    ${systemd_user_unitdir} \
    ${PYTHON_SITEPACKAGES_DIR} \
"

# In code, path to python is a variable that is replaced with path to native version of it
# during the configure stage, e.g ../recipe-sysroot-native/usr/bin/python3-native/python3.
# Replace it with #!/usr/bin/env python3
do_install:append() {
    sed -i "1s/.*/#!\/usr\/bin\/env python3/" ${D}${prefix}/libexec/blueman-rfcomm-watcher \
                                              ${D}${prefix}/libexec/blueman-mechanism \
                                              ${D}${bindir}/blueman-adapters \
                                              ${D}${bindir}/blueman-applet \
                                              ${D}${bindir}/blueman-manager \
                                              ${D}${bindir}/blueman-sendto \
                                              ${D}${bindir}/blueman-services \
                                              ${D}${bindir}/blueman-tray
}

