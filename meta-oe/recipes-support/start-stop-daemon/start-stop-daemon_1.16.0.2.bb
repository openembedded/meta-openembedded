SUMMARY = "Debian's start-stop-daemon utility extracted from the dpkg \
package"
LICENSE = "PD"
LIC_FILES_CHKSUM = "file://utils/start-stop-daemon.c;md5=a963623e4588f70122865aaa7a370ce4"
# start-stop-daemon is usually shipped by dpkg
DEPENDS = "ncurses"
RCONFLICS_${PN} = "dpkg" 

SRC_URI = "http://sources.openembedded.org/dpkg_${PV}.tar.bz2"
SRC_URI[md5sum] = "d211a84f38987771a49ad1c0f144334a"
SRC_URI[sha256sum] = "2a3d4ba83c743b3f004533fdd52372cb7b22f5c1da2042d0a31bbcc2b54c0ea5"

inherit autotools gettext pkgconfig

S = "${WORKDIR}/dpkg-${PV}"

EXTRA_OECONF = " \
    --with-start-stop-daemon \
    --without-bz2 \
    --without-install-info \
    --without-selinux \
    --without-update-alternatives \
"

do_install_append () {
    # remove everything that is not related to start-stop-daemon, since there
    # is no explicit rule for only installing ssd
    find ${D} -type f -not -name "*start-stop-daemon*" -exec rm {} \;
    find ${D} -depth -type d -empty -exec rmdir {} \;
}
