SUMMARY = "Terminal multiplexer"
HOMEPAGE = "https://github.com/tmux/tmux/wiki"
SECTION = "console/utils"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://tmux.c;beginline=3;endline=17;md5=f256b76d52e7b4d02bf19144bdaca107"

DEPENDS = "ncurses libevent bison-native"

SRC_URI = "https://github.com/tmux/tmux/releases/download/${PV}/tmux-${PV}.tar.gz"
SRC_URI[sha256sum] = "e79c699c7e949dccd0a4a125e17b8d1261e311b16979dbc8eb34542f3966d82e"

UPSTREAM_CHECK_URI = "https://github.com/tmux/tmux/releases"
UPSTREAM_CHECK_REGEX = "releases/tag/(?P<pver>\d+(\.\d+)+[a-z]?)$"

inherit autotools pkgconfig

PACKAGECONFIG ??= ""
PACKAGECONFIG[utempter] = "ac_cv_header_utempter_h=yes,ac_cv_header_utempter_h=no,libutempter,"
PACKAGECONFIG[sixel] = "--enable-sixel,--disable-sixel"

do_configure:prepend() {
    # The 'compat' directory is needed for output during the build but it's
    # not automatically created when building outside the source directory.
    mkdir -p ${B}/compat
}
