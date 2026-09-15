SUMMARY = "Password and keyring managing daemon"
HOMEPAGE = "http://www.gnome.org/"
BUGTRACKER = "https://bugzilla.gnome.org/"
SECTION = "x11/gnome"

LICENSE = "GPL-2.0-or-later AND LGPL-2.0-or-later AND LGPL-2.1-or-later"
LIC_FILES_CHKSUM = " \
    file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://COPYING.LIB;md5=4fbd65380cdd255951079008b364516c \
"

CVE_PRODUCT = "gnome-keyring gnome_keyring"

DEPENDS = " \
    glib-2.0-native \
    gcr3 \
    libgcrypt \
    p11-kit \
"

inherit gnomebase gsettings gettext

SRC_URI[archive.sha256sum] = "2aebaa2d474cc31507c87a7bbbdb3e16dbe26b1cfef9f206457f3f9df43558b0"
SRC_URI += "file://0001-meson-allow-setting-the-paths-to-ssh-agent-and-ssh-add-by-option.patch"

PACKAGECONFIG ??= " \
    libcap-ng \
    ssh-agent \
    ${@bb.utils.filter('DISTRO_FEATURES', 'pam selinux systemd', d)} \
"
PACKAGECONFIG[libcap-ng] = "-Dlibcap-ng=enabled,-Dlibcap-ng=disabled,libcap-ng"
PACKAGECONFIG[pam] = "-Dpam=true,-Dpam=false,libpam"
PACKAGECONFIG[selinux] = "-Dselinux=enabled,-Dselinux=disabled,libselinux"
PACKAGECONFIG[ssh-agent] = "-Dssh-agent=true -Dssh-agent-path=${bindir}/ssh-agent -Dssh-add-path=${bindir}/ssh-add,-Dssh-agent=false,,openssh-misc"
PACKAGECONFIG[systemd] = "-Dsystemd=enabled,-Dsystemd=disabled,systemd"

EXTRA_OEMESON = " \
    -Dmanpage=false \
    -Dpkcs11-config=${datadir}/p11-kit/modules \
    -Dpkcs11-modules=${libdir}/pkcs11 \
"

FILES:${PN} += " \
    ${datadir}/dbus-1/services \
    ${datadir}/p11-kit \
    ${datadir}/xdg-desktop-portal \
    ${libdir}/security/*${SOLIBSDEV} \
    ${libdir}/pkcs11/gnome-keyring-pkcs11.so \
    ${systemd_user_unitdir} \
"
# fix | gnome-keyring-daemon: insufficient process capabilities, unsecure memory might get used
pkg_postinst:${PN} () {
    setcap cap_ipc_lock+ep $D/${bindir}/gnome-keyring-daemon
}
PACKAGE_WRITE_DEPS += "libcap-native"
