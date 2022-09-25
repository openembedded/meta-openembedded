SUMMARY = "Linux kernel trace file system library"

LICENSE = "GPL-2.0-only & LGPL-2.1-only"
LIC_FILES_CHKSUM = " \
    file://LICENSES/GPL-2.0;md5=e6a75371ba4d16749254a51215d13f97 \
    file://LICENSES/LGPL-2.1;md5=b370887980db5dd40659b50909238dbd \
    "

DEPENDS = "libtraceevent"

SRC_URI = " \
    git://git.kernel.org/pub/scm/libs/libtrace/libtracefs.git;branch=libtracefs;protocol=https \
    file://0001-libtraceevent-make-it-possible-to-set-libdir-include.patch \
    "

SRCREV = "5ba3308a912c4e1987d993a68aa35f217acdc728"

S = "${WORKDIR}/git"

inherit pkgconfig

do_install () {
    oe_runmake 'DESTDIR=${D}' install
    # Because makefile uses cp instead of install we need to change owner of files
    chown -R root:root ${D}${libdir}
    # Dirty hack to move .pc file to correct place
    mkdir -p ${D}${libdir}/pkgconfig
    find ${D} -type f -name ${PN}.pc -exec mv {} ${D}${libdir}/pkgconfig/ \; -quit
    rm -rf ${D}/`echo ${TMPDIR} | cut -d/ -f2`
}
