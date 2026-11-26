DESCRIPTION = "Utilities to collect and visualise system statistics"
HOMEPAGE = "http://www.i-scream.org/libstatgrab/"

LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=570a9b3749dd0463a1778803b12a6dce"

DEPENDS = "ncurses"

PACKAGES:prepend = "statgrab statgrab-dbg saidar saidar-dbg ${PN}-mrtg "

# don't use the git tag unless docbooc2x is available to build the manpages
SRC_URI = "https://github.com/libstatgrab/libstatgrab/releases/download/LIBSTATGRAB_${@d.getVar('PV').replace('.', '_')}/libstatgrab-${PV}.tar.gz \
          "
SRC_URI[sha256sum] = "ff559fcd4da75a4023af986c02c52ad51d4b68ae83fc0c8c461026126a8ebefa"

UPSTREAM_CHECK_URI = "https://github.com/${BPN}/${BPN}/releases"
UPSTREAM_CHECK_REGEX = "releases/tag/LIBSTATGRAB_(?P<pver>\d+(_\d+)+)"

# Perl5 is for tests only
EXTRA_OECONF = "--without-perl5 --with-mnttab=/proc/mounts"

# use with caution - default properties are configured to trace
PACKAGECONFIG ??= ""
PACKAGECONFIG[log4cplus] = "--with-log4cplus,--without-log4cplus,log4cplus"
PACKAGECONFIG[logging] = "--enable-logging,--disable-logging,"

inherit autotools pkgconfig

FILES:statgrab = "${bindir}/statgrab"
FILES:statgrab-dbg = "${bindir}/.debug/statgrab"
FILES:saidar = "${bindir}/saidar"
FILES:saidar-dbg = "${bindir}/.debug/saidar"
FILES:${PN}-mrtg = "${bindir}/statgrab-make-mrtg-config ${bindir}/statgrab-make-mrtg-index"
RDEPENDS:${PN}-mrtg:append = " perl statgrab"
