DESCRIPTION = "Lightweight, cross-platform integrated development environment"

HOMEPAGE = "http://qt-project.org/"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://LGPL_EXCEPTION.TXT;md5=eb6c371255e1262c55ae9b652a90b528 \
                    file://LICENSE.LGPL;md5=243b725d71bb5df4a1e5920b344b86ad"
SECTION = "qt/app"

SRC_URI = "http://download.qt-project.org/official_releases/qtcreator/2.7/${PV}/${BP}-src.tar.gz"
SRC_URI[md5sum] = "7f1c10740784d3edf2347c27be21fd1a"
SRC_URI[sha256sum] = "5df913faa43a5fbf44fb1f25faaf6d258134b8f9e6b8ef8a21277136dec9e189"

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
