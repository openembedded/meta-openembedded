SUMMARY = "NetworkManager-openvpn-plugin"
SECTION = "net/misc"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=100d5a599bead70ddcd70dcd73f2e29c"

DEPENDS = "dbus dbus-glib networkmanager openvpn"

inherit gnomebase useradd gettext systemd

SRC_URI = "${GNOME_MIRROR}/NetworkManager-openvpn/${@gnome_verdir("${PV}")}/NetworkManager-openvpn-${PV}.tar.xz"

SRC_URI[md5sum] = "02931c0302310d06e43d413b5a7a3261"
SRC_URI[sha256sum] = "c982b644fa43d3019cb654a17e692bb3a7175a86d28065ea51f55ae7dfe45d8c"

S = "${WORKDIR}/NetworkManager-openvpn-${PV}"

PACKAGECONFIG[gnome] = "--with-gnome,--without-gnome"

# Create user and group nm-openvpn that are needed since version 1.0.6
USERADD_PACKAGES = "${PN}"
USERADD_PARAM_${PN} = "--system nm-openvpn"

FILES_${PN} += " \
    ${libdir}/NetworkManager/*.so \
"

RDEPENDS_${PN} = " \
    networkmanager \
    openvpn \
"
