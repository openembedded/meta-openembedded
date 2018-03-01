SUMMARY = "Pulseaudio mixer for the xfce panel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=f5eac6bb0d6ec0dc655e417781d4015f"

inherit xfce-panel-plugin distro_features_check

REQUIRED_DISTRO_FEATURES = "pulseaudio x11"

DEPENDS += "pulseaudio"

SRC_URI[md5sum] = "30cd40be36895c4ced48c2433ff440c4"
SRC_URI[sha256sum] = "25e7bc414edf6e16140c31ca4e7dcedd4e17d40ea23a2921beb799fed11a99bb"

PACKAGECONFIG ??= ""
PACKAGECONFIG[libnotify] = "--enable-libnotify,--disable-libnotify,libnotify"

RRECOMMENDS_${PN} = "pavucontrol"
