DESCRIPTION = "e-tasks is a todo program for Openmoko phones"
HOMEPAGE = "http://code.google.com/p/e-tasks/"
AUTHOR = "cchandel"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=8f0e2cd40e05189ec81232da84bd6e1a"
SECTION = "e/apps"
DEPENDS = "elementary eina edbus sqlite3"

inherit autotools

SRCREV = "25"
PV = "0.0.1+svnr${SRCPV}"
PR = "r10"

SRC_URI = "svn://e-tasks.googlecode.com/svn;module=trunk;proto=http \
           file://genlist_item_insert_before.api.patch \
           file://0001-adapt-to-newer-elm-API.patch \
           file://depends.eina.patch"
S = "${WORKDIR}/trunk"

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
