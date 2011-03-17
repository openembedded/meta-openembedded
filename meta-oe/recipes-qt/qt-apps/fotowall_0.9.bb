DESCRIPTION = "Fotowall is a creative tool that allows you to layout your photos or pictures \
in a personal way. You can add pictures, then resize, move, change colors, text, shadows, etc.."

HOMEPAGE = "http://www.enricoros.com/opensource/fotowall"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://GPL_V2;md5=79808397c3355f163c012616125c9e26 \
                    file://main.cpp;beginline=6;endline=11;md5=b569acc2bf8974a3082b58fc53b9d8dc"
SECTION = "x11/apps"

PR = "r1"

inherit qt4x11 pkgconfig

RRECOMMENDS_${PN} += "qt4-plugin-imageformat-gif qt4-plugin-imageformat-jpeg qt4-plugin-imageformat-tiff"

SRC_URI = "http://qt-apps.org/CONTENT/content-files/71316-Fotowall-0.9.tar.bz2"

SRC_URI[md5sum] = "142ef697332e0777c6d22c5bc96cc438"
SRC_URI[sha256sum] = "e4d0c005d2cb1d7c09438bfc3098eadebc08946e4fbc0655b7fc8b046de3810d"

S = "${WORKDIR}/Fotowall-${PV}"

do_install() {
	oe_runmake INSTALL_ROOT=${D} install
}
