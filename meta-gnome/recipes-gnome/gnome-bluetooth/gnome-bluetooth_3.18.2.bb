SUMMARY = "GNOME bluetooth manager"
LICENSE = "GPLv2 & LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=eb723b61539feef013de476e68b5c50a \
                    file://COPYING.LIB;md5=a6f89e2100d9b6cdffcea4f398e37343 \
"

SECTION = "x11/gnome"

# systemd is needed because it has a necessary version of libudev
# Effectively this means that systemd must be in DISTRO_FEATURES
DEPENDS = "systemd gtk+3 libnotify libcanberra"
DEPENDS += "${@bb.utils.contains('DISTRO_FEATURES','bluez5','bluez5','bluez4',d)}"

inherit gnomebase gtk-icon-cache distro_features_check
REQUIRED_DISTRO_FEATURES = "systemd"

SRC_URI[archive.md5sum] = "75d09c924468ec0c687f9ab3acf7f113"
SRC_URI[archive.sha256sum] = "d8df073c331df0f97261869fb77ffcdbf4e3e4eaf460d3c3ed2b16e03d9c5398"

FILES_${PN}-dbg += "${libdir}/gnome-bluetooth/plugins/.debug/"
