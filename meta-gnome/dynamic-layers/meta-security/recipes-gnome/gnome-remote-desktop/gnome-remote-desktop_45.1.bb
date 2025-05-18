SUMMARY = "Remote desktop daemon for GNOME using pipewire."
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

GNOMEBASEBUILDCLASS = "meson"

inherit gnomebase gettext gsettings features_check

REQUIRED_DISTRO_FEATURES = "opengl"

SRC_URI[archive.sha256sum] = "dcd9c18ac2306695631fcf00a88645c38e370eba05c69df39f540204d4eafd8d"

DEPENDS = " \
    asciidoc-native \
    libdrm \
    libei \
    libepoxy \
    cairo \
    glib-2.0 \
    pipewire \
    libnotify \
    libsecret \
    nv-codec-headers \
    tpm2-tss \
"

PACKAGECONFIG ??= " \
    vnc \
    rdp \
    ${@bb.utils.contains('LICENSE_FLAGS_ACCEPTED', 'commercial', 'fdk_aac', '', d)} \
    ${@bb.utils.filter('DISTRO_FEATURES', 'systemd', d)} \
"

PACKAGECONFIG[tests] = "-Dtests=true,-Dtests=false,pipewire-native wireplumber-native dbus-native"
PACKAGECONFIG[vnc] = "-Dvnc=true,-Dvnc=false,libvncserver"
PACKAGECONFIG[rdp] = "-Drdp=true,-Drdp=false,freerdp fuse3 libxkbcommon"
PACKAGECONFIG[fdk_aac] = "-Dfdk_aac=true,-Dfdk_aac=false,fdk-aac"
PACKAGECONFIG[systemd] = "-Dsystemd=true,-Dsystemd=false,systemd"

FILES:${PN} += "${systemd_user_unitdir}"
