DESCRIPTION = "Lightweight, cross-platform integrated development environment"

HOMEPAGE = "http://qt-project.org/"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://LGPL_EXCEPTION.TXT;md5=eb6c371255e1262c55ae9b652a90b528 \
                    file://LICENSE.LGPL;md5=243b725d71bb5df4a1e5920b344b86ad"
SECTION = "qt/app"

SRC_URI = "http://download.qt-project.org/official_releases/qtcreator/2.8/${PV}/${BP}-src.tar.gz \
           file://fix.missing.cpuid.h.patch \
           file://qbs_transformer_product.patch"
SRC_URI[md5sum] = "79ef6c6ece0c00035ef744c9d6e3bd3b"
SRC_URI[sha256sum] = "d5ae007a297a4288d0e95fd605edbfb8aee80f6788c7a6cfb9cb297f50c364b9"

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
                      packagegroup-qt-toolchain-target \
                      qt4-plugin-sqldriver-sqlite \
"
