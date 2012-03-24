DESCRIPTION = "e-tasks is a todo program for Openmoko phones"
HOMEPAGE = "http://code.google.com/p/e-tasks/"
AUTHOR = "cchandel"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=8f0e2cd40e05189ec81232da84bd6e1a"
SECTION = "e/apps"
DEPENDS = "elementary eina edbus sqlite3"

inherit autotools

SRCREV = "b640f0e7287877983645d9d9a36f85a0b6a54631"
PV = "0.0.2+gitr${SRCPV}"
PR = "r1"

SRC_URI = "git://github.com/shr-project/e-tasks.git;protocol=git;branch=master \
"
S = "${WORKDIR}/git"

do_install_append() {
        install -d "${D}/${datadir}/pixmaps"
        install -m 0644 "${S}/resources/e-tasks.png" "${D}/${datadir}/pixmaps"
        install -d "${D}/${datadir}/applications"
        install -m 0644 "${S}/resources/e-tasks.desktop" "${D}/${datadir}/applications"
        install -d  "${D}/${datadir}/e-tasks"
        for ico in "${S}/resources/"*.png; do
                if [ "$(basename $ico)" != "e-tasks.png" ]; then
                        install -m 0644 $ico "${D}/${datadir}/e-tasks"
                fi
        done
}

FILES_${PN} += "/usr/share/e-tasks/* /usr/share/applications/* /usr/share/pixmaps/*"
