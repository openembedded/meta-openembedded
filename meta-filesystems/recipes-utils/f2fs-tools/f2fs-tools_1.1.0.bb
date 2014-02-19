SUMMARY = "Tools for Flash-Friendly File System (F2FS)"
HOMEPAGE = "http://sourceforge.net/projects/f2fs-tools/"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=15667d9b3cb737b57471c148b7c50734"

# to provide libuuid
DEPENDS = "util-linux"

SRCREV = "da59f6146c37e727bb83ae4922ca56d42958e61c"
SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/jaegeuk/f2fs-tools.git"
S = "${WORKDIR}/git"

inherit autotools

BBCLASSEXTEND = "native"

do_configure_prepend() {
    # workaround for endless do_configure loop:
    # make: Warning: File `Makefile.am' has modification time 5.3e+04 s in the future
    touch ${S}/*
}
