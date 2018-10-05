SUMMARY = "A curses client for the Music Player Daemon"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"
HOMEPAGE = "https://www.musicpd.org/clients/ncmpc/"

inherit meson

DEPENDS += " \
    boost \
    pcre \
    ncurses \
    libmpdclient \
"

PACKAGECONFIG ??= "colors locale mouse nls help_screen artist_screen search_screen song_screen key_screen lyrics_screen outputs_screen"

PACKAGECONFIG[colors] = "-Dcolors=true,-Dcolors=false"
PACKAGECONFIG[lirc] = "-Dlirc=true,-Dlirc=false,lirc"
PACKAGECONFIG[locale] = "-Dlocale=true,-Dlocale=false"
PACKAGECONFIG[mini] = "-Dmini=true,-Dmini=false"
PACKAGECONFIG[mouse] = "-Dmouse=true,-Dmouse=false"
PACKAGECONFIG[nls] = "-Dnls=true,-Dnls=false,gettext-native"

PACKAGECONFIG[help_screen] = "-Dhelp_screen=true,-Dhelp_screen=false"
PACKAGECONFIG[artist_screen] = "-Dartist_screen=true,-Dartist_screen=false"
PACKAGECONFIG[search_screen] = "-Dsearch_screen=true,-Dsearch_screen=false"
PACKAGECONFIG[song_screen] = "-Dsong_screen=true,-Dsong_screen=false"
PACKAGECONFIG[key_screen] = "-Dkey_screen=true,-Dkey_screen=false"
PACKAGECONFIG[lyrics_screen] = "-Dlyrics_screen=true,-Dlyrics_screen=false"
PACKAGECONFIG[outputs_screen] = "-Doutputs_screen=true,-Doutputs_screen=false"
PACKAGECONFIG[chat_screen] = "-Dchat_screen=true,-Dchat_screen=false"

SRC_URI = " \
    git://github.com/MusicPlayerDaemon/ncmpc \
"
SRCREV = "b03703653d3f4a418c4a8b6a9e32834dd882a185"
S = "${WORKDIR}/git"
