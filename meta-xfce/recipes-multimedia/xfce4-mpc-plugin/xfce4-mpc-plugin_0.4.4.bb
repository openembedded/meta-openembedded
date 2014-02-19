SUMMARY = "Simple client plugin for Music Player Daemon"
HOMEPAGE = "http://goodies.xfce.org/projects/panel-plugins/xfce4-mpc-plugin"
SECTION = "x11/application"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=3604d987e6dfdfc672c754d08953b0e0"

inherit xfce-panel-plugin

DEPENDS += "libmpd"

# for now we recommend our own mpd-server
RRECOMMENDS_${PN} = "mpd"

SRC_URI[md5sum] = "1c126ca71d6db1ac6a0cc6720554e7bd"
SRC_URI[sha256sum] = "4f0ce6146aa2680a4c1ca7bad1be30187a8a179e8c4355a8fe13d1fc38dac5c5"
