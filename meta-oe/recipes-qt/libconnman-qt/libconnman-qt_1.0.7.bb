DESCRIPTION = "Qt Library for ConnMan"
HOMEPAGE = "https://github.com/nemomobile/libconnman-qt"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://libconnman-qt/clockmodel.h;endline=8;md5=ea9f724050803f15d2d900ce3c5dac88"

SECTION = "qt/lib"


inherit qt4x11 pkgconfig

RDEPENDS_${PN} = "connman"
RDEPENDS_${PN}-plugin = "${PN}"

PROVIDES += "${PN}-plugin"

PACKAGES =+ "${PN}-plugin ${PN}-plugin-dbg"
RRECOMMENDS_${PN} = "${PN}-plugin"

BRANCH = "master"
SRCREV = "6a5e4f981112a90a8c19d82bd671da389d2af993"

SRC_URI = " \
    git://github.com/nemomobile/${PN}.git;branch=${BRANCH} \
"

S = "${WORKDIR}/git"

QT_IMPORTS_DIR = "${libdir}/qt4/imports"
# the plugin target needs to be the same as 'target.path' and 'qmldir.path' in ${S}/plugin/plugin.pro
PLUGINS_TARGET = "${QT_IMPORTS_DIR}/MeeGo/Connman"

EXTRA_QMAKEVARS_PRE = " \
    CONFIG+=notest \
"

do_configure_prepend() {
    # Hack *.pro variables
    find ${S}/plugin/*.pro -exec sed -i -e 's,$$\[QT_INSTALL_IMPORTS\],${QT_IMPORTS_DIR},g' '{}' ';'
}

do_install() {
    export INSTALL_ROOT=${D}
    oe_runmake install
}

FILES_${PN} = " \
    ${libdir}/libconnman-qt4${SOLIBS} \
"

FILES_${PN}-dev = " \
    ${includedir}/connman-qt/* \
    ${libdir}/libconnman-qt4${SOLIBSDEV} \
    ${libdir}/libconnman-qt4.prl \
    ${libdir}/pkgconfig/connman-qt4.pc \
    ${libdir}/connman-qt4.pc \
"

FILES_${PN}-plugin = " \
    ${PLUGINS_TARGET}/qmldir \
    ${PLUGINS_TARGET}/lib*.so \
"

FILES_${PN}-plugin-dbg = " \
    ${PLUGINS_TARGET}/.debug \
    ${PLUGINS_TARGET}/.debug/* \
"
