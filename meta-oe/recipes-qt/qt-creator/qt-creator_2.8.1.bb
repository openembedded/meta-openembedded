SUMMARY = "Lightweight, cross-platform integrated development environment"

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

EXTRA_QMAKEVARS_PRE += "IDE_LIBRARY_BASENAME=${baselib}"

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

# avoid conflicts with meta-qt5's qt-creator and do install nothing to sysroot
# this does no harm cause nothing depends on qt-creator
sysroot_stage_all() {
}

# the regexp in insane.bbclass doesn't allow this valid path:
# qt-creator-2.8.1: qt-creator: found library in wrong location: /usr/share/qtcreator/qbs/lib/qbs/plugins/libqbs_cpp_scanner.so
# qt-creator: found library in wrong location: /usr/share/qtcreator/qbs/lib/qbs/plugins/libqbs_qt_scanner.so
# qt-creator-dbg: found library in wrong location: /usr/share/qtcreator/qbs/lib/qbs/plugins/.debug/libqbs_cpp_scanner.so
# qt-creator-dbg: found library in wrong location: /usr/share/qtcreator/qbs/lib/qbs/plugins/.debug/libqbs_qt_scanner.so
INSANE_SKIP_${PN} += "libdir"
INSANE_SKIP_${PN}-dbg += "libdir"

RDEPENDS_${PN} += "perl"

# /usr/include/qt4/QtCore/qsharedpointer_impl.h:336:11: error: 'product' may be used uninitialized in this function [-Werror=maybe-uninitialized]
PNBLACKLIST[qt-creator] ?= "BROKEN: fails to build with gcc-5 and conflicts with qt5-creator"
