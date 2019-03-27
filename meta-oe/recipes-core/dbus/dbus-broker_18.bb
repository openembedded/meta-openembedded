SUMMARY = "dbus broker"
DESCRIPTION = "Drop-in replacement for dbus-daemon."

SECTION = "base"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7b486c2338d225a1405d979ed2c15ce8"

SRC_URI = "https://github.com/bus1/dbus-broker/releases/download/v${PV}/dbus-broker-${PV}.tar.xz"
SRC_URI[sha256sum] = "f29e77a4d7b386e835dbe6379f4308f0503d6077834ba734ea6782359b34cbb9"
SRC_URI_append_libc-musl = "file://0001-c-ini-Fix-missing-sys-types.h-includes.patch"

inherit meson pkgconfig systemd distro_features_check

DEPENDS = "expat systemd"

REQUIRED_DISTRO_FEATURES = "systemd"

SYSTEMD_SERVICE_${PN} = "${BPN}.service"

FILES_${PN} += "${systemd_system_unitdir}"
FILES_${PN} += "${systemd_user_unitdir}"
