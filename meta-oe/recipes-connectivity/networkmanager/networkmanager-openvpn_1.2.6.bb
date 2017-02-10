SUMMARY = "NetworkManager-openvpn-plugin"
SECTION = "net/misc"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=100d5a599bead70ddcd70dcd73f2e29c"

DEPENDS = "dbus dbus-glib networkmanager openvpn intltool-native"

inherit gnomebase useradd gettext systemd

SRC_URI = "${GNOME_MIRROR}/NetworkManager-openvpn/${@gnome_verdir("${PV}")}/NetworkManager-openvpn-${PV}.tar.xz"
SRC_URI[md5sum] = "47ed9b6c43ca364976a15e84207687df"
SRC_URI[sha256sum] = "2373e2bb0a8a876cb2997cd8b0e3d6e10012d9bef3705ea3ac21f6394b3f1fb0"

S = "${WORKDIR}/NetworkManager-openvpn-${PV}"

PACKAGECONFIG[gnome] = "--with-gnome,--without-gnome"

do_install_append () {
    rm -rf ${D}${libdir}/NetworkManager/*.la
}

# Create user and group nm-openvpn that are needed since version 1.0.6
USERADD_PACKAGES = "${PN}"
USERADD_PARAM_${PN} = "--system nm-openvpn"

FILES_${PN} += " \
    ${libdir}/NetworkManager/*.so \
    ${libdir}/NetworkManager/VPN/nm-openvpn-service.name \
"

RDEPENDS_${PN} = " \
    networkmanager \
    openvpn \
"

PNBLACKLIST[networkmanager-openvpn] ?= "Fails to build with RSS http://errors.yoctoproject.org/Errors/Details/130667/"
