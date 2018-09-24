SUMMARY = "A curses client for the Music Player Daemon"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"
HOMEPAGE = "https://www.musicpd.org/clients/ncmpc/"

inherit meson

DEPENDS += " \
    gettext-native \
    glib-2.0 \
    ncurses \
    libmpdclient \
"

# We're using a 0.32 pre-release commit which contains a fix for a
# build failure.
PV = "0.32~git${SRCPV}"

SRC_URI = " \
    git://github.com/MusicPlayerDaemon/ncmpc \
"
SRCREV = "1150f7931de7588643437069dd798724dc1ceb47"
S = "${WORKDIR}/git"
