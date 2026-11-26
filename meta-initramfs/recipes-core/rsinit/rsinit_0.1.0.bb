SUMMARY = "minimalistic init written in rust"
DESCRIPTION = "rsinit is a minimalistic single binary init for the initramfs for embedded systems"
HOMEPAGE = "https://github.com/pengutronix/rsinit"

LICENSE = "GPL-2.0-only AND CC0-1.0"
LIC_FILES_CHKSUM = "file://LICENSES/GPL-2.0-only.txt;md5=570a9b3749dd0463a1778803b12a6dce \
                    file://LICENSES/CC0-1.0.txt;md5=65d3616852dbf7b1a6d4b53b00626032"

SRC_URI = "git://github.com/pengutronix/rsinit.git;protocol=https;branch=main;tag=v${PV}"
SRCREV = "91732bdc0364e0bd0bc3a77dbedfa3a1dd330a1a"

UPSTREAM_CHECK_GITTAGREGEX = "^v(?P<pver>\d+(\.\d+)+)$"

inherit cargo cargo-update-recipe-crates

require ${BPN}-crates.inc

INSANE_SKIP:${PN} += "already-stripped"

PACKAGECONFIG ??= " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'systemd', '', d)} \
    reboot \
"

PACKAGECONFIG[defaults] = ",--no-default-features"
PACKAGECONFIG[dmverity] = "--features dmverity"
PACKAGECONFIG[reboot] = "--features reboot-on-failure"
PACKAGECONFIG[systemd] = "--features systemd"
PACKAGECONFIG[usb9pfs] = "--features usb9pfs"

# cargo installs everything into ${bindir}, which is normally /usr/bin.
# Not the best choice for the init system, which should reside under /init
bindir = ""
