DESCRIPTION = "Lightweight, cross-platform integrated development environment"

HOMEPAGE = "http://qt-project.org/"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://LGPL_EXCEPTION.TXT;md5=eb6c371255e1262c55ae9b652a90b528 \
                    file://LICENSE.LGPL;md5=243b725d71bb5df4a1e5920b344b86ad"
SECTION = "qt/app"

SRC_URI = "http://download.qt-project.org/official_releases/qtcreator/2.8/${PV}/${BP}-src.tar.gz"
SRC_URI[md5sum] = "5aacdad4491b7dda9758a81384d8da79"
SRC_URI[sha256sum] = "7ac5d9a36c2f561f74d77378d4eae95a78c7752b323e1df924d6e895e99f45d2"

S = "${WORKDIR}/${BP}-src"

inherit qt4x11

do_install() {
	oe_runmake INSTALL_ROOT=${D}${prefix} install
	oe_runmake INSTALL_ROOT=${D}${prefix} install_docs
	rm -f ${D}${libdir}/qtcreator/lib*.so
}

FILES_${PN} += "${datadir}/icons \
                ${datadir}/qtcreator \
                ${libdir}/qtcreator/*"
FILES_${PN}-dbg += "${datadir}/qtcreator/*/*/*/*/.debug \
                    ${libdir}/qtcreator/.debug \
                    ${libdir}/qtcreator/*/*/.debug"
RRECOMMENDS_${PN} += "packagegroup-core-buildessential \
                      packagegroup-qt-toolchain-target"
