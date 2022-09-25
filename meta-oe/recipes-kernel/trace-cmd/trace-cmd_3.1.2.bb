SUMMARY = "User-space front-end command-line tool for ftrace"

LICENSE = "GPL-2.0-only & LGPL-2.1-only"
LIC_FILES_CHKSUM = " \
    file://LICENSES/GPL-2.0;md5=e6a75371ba4d16749254a51215d13f97 \
    file://LICENSES/LGPL-2.1;md5=b370887980db5dd40659b50909238dbd \
    "

DEPENDS = "libtraceevent libtracefs zstd swig-native bison-native"

SRC_URI = " \
    git://git.kernel.org/pub/scm/linux/kernel/git/rostedt/trace-cmd.git;branch=master \
    file://0001-trace-cmd-make-it-build-against-musl-C-library.patch \
    "

SRCREV = "d4cbf2c8ba2b8f27020085bc49c982664ae9e04f"

S = "${WORKDIR}/git"

inherit bash-completion pkgconfig

do_install () {
    oe_runmake 'DESTDIR=${D}' install
}

