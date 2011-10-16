DESCRIPTION = "Collection of hacks to speed up systemd boot"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58"

PR = "r1"

inherit allarch

ALLOW_EMPTY_${PN} = "1"

pkg_postinst_${PN} () {
#!/bin/sh
# I can run offline
rm -f $D/lib/systemd/system/sysinit.target.wants/systemd-tmpfiles-setup.service

rm -f $D/lib/systemd/system/sysinit.target.wants/sys-fs-fuse-connections.mount
rm -f $D/lib/systemd/system/sysinit.target.wants/sys-kernel-debug.mount
}
