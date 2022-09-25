SUMMARY = "Library that provides APIs to access kernel tracepoint events"

LICENSE = "GPL-2.0-only & LGPL-2.1-only"
LIC_FILES_CHKSUM = " \
    file://LICENSES/GPL-2.0;md5=e6a75371ba4d16749254a51215d13f97 \
    file://LICENSES/LGPL-2.1;md5=b370887980db5dd40659b50909238dbd \
    "

SRC_URI = " \
    git://git.kernel.org/pub/scm/libs/libtrace/libtraceevent.git;branch=libtraceevent;protocol=https \
    file://0001-libtraceevent-make-it-possible-to-set-libdir-and-man.patch \
    "

SRCREV = "424b11f6c3ab2e3bb199180e142fbcf9eebf2ac3"

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

PACKAGES += "${PN}-plugins"

FILES:${PN}-plugins += "${libdir}/traceevent/plugins"
