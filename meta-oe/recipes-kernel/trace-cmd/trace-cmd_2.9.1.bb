SUMMARY = "User-space front-end command-line tool for ftrace"

LICENSE = "GPL-2.0-only & LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=873f48a813bded3de6ebc54e6880c4ac"

SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/rostedt/trace-cmd.git;branch=master \
           file://0001-trace-cmd-make-it-build-with-musl.patch \
           file://0002-replace-off64_t-and-lseek64.patch \
           "

SRCREV = "530b1a0caef39466e16bbd49de5afef89656f03f"

S = "${WORKDIR}/git"

DEPENDS += "libtraceevent libtracefs zstd xmlto-native asciidoc-native"

inherit pkgconfig

TARGET_CC_ARCH += "${LDFLAGS}"

do_compile() {
        oe_runmake libdir_relative=${BASELIB} libs
        oe_runmake libdir_relative=${BASELIB} all
}

do_install() {
       oe_runmake libdir_relative=${BASELIB} etcdir=${sysconfdir} DESTDIR=${D} install install_libs
       mkdir -p ${D}${libdir}/traceevent/plugins/${BPN}
       mv ${D}/${libdir}/traceevent/plugins/*.so ${D}${libdir}/traceevent/plugins/${BPN}/
}

FILES:${PN} += "${libdir}/traceevent ${libdir}/traceevent/plugins ${libdir}/tracefs"
