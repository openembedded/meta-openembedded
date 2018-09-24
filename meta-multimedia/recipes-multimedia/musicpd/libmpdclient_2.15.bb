SUMMARY = "C client library for the Music Player Daemon"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=06b9dfd2f197dc514d8ef06549684b77"
HOMEPAGE = "https://www.musicpd.org/libs/libmpdclient/"

inherit meson

SRC_URI = " \
    git://github.com/MusicPlayerDaemon/libmpdclient \
"
SRCREV = "7754ffdfb9371cd398371cb2670a54e9e1e623e2"
S = "${WORKDIR}/git"

do_install_append() {
    # libmpdclient's Vala bindings are outdated and unmaintained; it
    # is likely that nobody will ever use them, so let's not install
    # them
    rm -rf ${D}${datadir}/vala
}
