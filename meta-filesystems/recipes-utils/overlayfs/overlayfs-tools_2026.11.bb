DESCRIPTION = "Maintenance tools for OverlayFS"
HOMEPAGE = "https://github.com/kmxz/overlayfs-tools"
LICENSE = "WTFPL"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=f312a7c4d02230e8f2b537295d375c69"

SRC_URI = "\
    git://github.com/kmxz/overlayfs-tools.git;protocol=https;branch=master;tag=v${PV} \
"

SRCREV = "42582352e11b162895c06826f18a2246d8005ca9"

UPSTREAM_CHECK_GITTAGREGEX = "v(?P<pver>\d+(\.\d+)+)"

inherit meson pkgconfig

DEPENDS:append:libc-musl = " fts"
