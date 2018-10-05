SUMMARY = "A curses client for the Music Player Daemon"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"
HOMEPAGE = "https://www.musicpd.org/clients/ncmpc/"

inherit meson

DEPENDS += " \
    gettext-native \
    boost \
    pcre \
    ncurses \
    libmpdclient \
"

SRC_URI = " \
    git://github.com/MusicPlayerDaemon/ncmpc \
"
SRCREV = "b03703653d3f4a418c4a8b6a9e32834dd882a185"
S = "${WORKDIR}/git"
