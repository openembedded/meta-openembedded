SUMMARY = "Sync files and directories to and from cloud storage providers"
HOMEPAGE = "https://rclone.org"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/COPYING;md5=bed161b82a1ecab65ff7ba3c3b960439"

SRC_URI = "git://${GO_IMPORT};protocol=https;nobranch=1;tag=v${PV};destsuffix=${GO_SRCURI_DESTSUFFIX}"
SRCREV = "e982ff36668f5130d94b4f70409babb3d964df42"

require ${BPN}-licenses.inc
require ${BPN}-go-mods.inc

GO_IMPORT = "github.com/rclone/rclone"
GO_INSTALL = "${GO_IMPORT}"

inherit go-mod go-mod-update-modules

GO_EXTRA_LDFLAGS = "-X ${GO_IMPORT}/fs.Version=v${PV}"

RRECOMMENDS:${PN} += "fuse3"

RDEPENDS:${PN}-dev += "bash python3-core"
