SUMMARY = "Pulseaudio mixer for the xfce panel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=f5eac6bb0d6ec0dc655e417781d4015f"

inherit xfce-panel-plugin distro_features_check

REQUIRED_DISTRO_FEATURES = "pulseaudio"

DEPENDS += "pulseaudio"

SRC_URI[md5sum] = "13a47ba5a72c16fb06f7e1cc89858cc7"
SRC_URI[sha256sum] = "e82836bc8cf7d905b4e60d43dc630ba8e32dea785989700c71d4aeee9f583b33"

PACKAGECONFIG ??= ""
PACKAGECONFIG[libnotify] = "--enable-libnotify,--disable-libnotify,libnotify"

RRECOMMENDS_${PN} = "pavucontrol"
