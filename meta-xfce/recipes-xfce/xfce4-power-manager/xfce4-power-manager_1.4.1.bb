SUMMARY = "Power manager for the Xfce desktop environment"
HOMEPAGE = "http://goodies.xfce.org/projects/applications/xfce4-power-manager"
SECTION = "x11"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"

inherit xfce

DEPENDS = "gtk+ glib-2.0 dbus-glib xfconf libxfce4ui libxfce4util libnotify \
           libxrandr virtual/libx11 libxext xfce4-panel"

SRC_URI[md5sum] = "808a2630487d75e6eae915e464b7fda3"
SRC_URI[sha256sum] = "16a9e9bf3e0f8480d05b248817fbdc59e701e9161d3d0c7e53f3de28ed491f58"

EXTRA_OECONF = " \
    --enable-network-manager \
    --enable-panel-plugins \
"

PACKAGECONFIG ??= "${@base_contains('DISTRO_FEATURES','systemd','systemd','',d)}"
PACKAGECONFIG[systemd] = "--enable-polkit, --disable-polkit, polkit"

PACKAGES += "xfce4-powermanager-plugin"

FILES_${PN} += " \
    ${datadir}/polkit-1 \
    ${datadir}/appdata \
"

FILES_xfce4-powermanager-plugin = " \
    ${libdir}/xfce4 \
    ${datadir}/xfce4 \
"

RDEPENDS_xfce4-powermanager-plugin = "${PN}"
RDEPENDS_${PN} = "networkmanager udisks upower ${@base_contains('DISTRO_FEATURES','systemd','','consolekit',d)}"

# xfce4-brightness-plugin was replaced by xfce4-powermanager-plugin
RPROVIDES_xfce4-powermanager-plugin += "xfce4-brightness-plugin"
RREPLACES_xfce4-powermanager-plugin += "xfce4-brightness-plugin"
RCONFLICTS_xfce4-powermanager-plugin += "xfce4-brightness-plugin"
