SUMMARY = "NetworkManager-openvpn-plugin"
SECTION = "net/misc"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=100d5a599bead70ddcd70dcd73f2e29c"

DEPENDS = "dbus dbus-glib networkmanager openvpn intltool-native glib-2.0-native"

inherit gnomebase useradd gettext systemd

SRC_URI = "${GNOME_MIRROR}/NetworkManager-openvpn/${@gnome_verdir("${PV}")}/NetworkManager-openvpn-${PV}.tar.xz"

SRC_URI[md5sum] = "4dbbc103761facc7a61a1c00dfd55231"
SRC_URI[sha256sum] = "af3cc86ba848d21b4ac807a09d575de11335ba4df8ce6fdb089212e77c2231ef"

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
    ${nonarch_libdir}/NetworkManager/VPN/nm-openvpn-service.name \
"

FILES_${PN}-staticdev += " \
    ${libdir}/NetworkManager/*.a \
"

RDEPENDS_${PN} = " \
    networkmanager \
    openvpn \
"
