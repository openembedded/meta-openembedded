DESCRIPTION = "Qt Library for ConnMan"
HOMEPAGE = "https://github.com/nemomobile/libconnman-qt"
SECTION = "qt/lib"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://libconnman-qt/clockmodel.h;endline=8;md5=ea9f724050803f15d2d900ce3c5dac88"
SRCREV = "80ac184d859ea90c026403f5a520644945a5081a"
PV = "1.0.50+git${SRCPV}"

SRC_URI = "git://github.com/nemomobile/libconnman-qt.git;branch=master"

S = "${WORKDIR}/git"

inherit pkgconfig

EXTRA_QMAKEVARS_PRE += "CONFIG+=notests TARGET_SUFFIX=${QT_DIR_NAME}"

do_configure_prepend() {
    # Hack *.pro variables
    find ${S}/plugin/*.pro -exec sed -i -e 's,$$\[QT_INSTALL_IMPORTS\],${QT_IMPORTS_DIR},g' '{}' ';'
}
do_install() {
    oe_runmake install INSTALL_ROOT=${D}
}

PACKAGES =+ "${PN}-plugin ${PN}-plugin-dbg"

RDEPENDS_${PN} = "connman"
RDEPENDS_${PN}-plugin = "${PN}"

RRECOMMENDS_${PN} = "${PN}-plugin"

FILES_${PN} = " \
    ${libdir}/libconnman-qt*${SOLIBS} \
"
FILES_${PN}-dev = " \
    ${includedir}/connman-qt*/* \
    ${libdir}/libconnman-qt*${SOLIBSDEV} \
    ${libdir}/libconnman-qt*.prl \
    ${libdir}/pkgconfig/connman-qt*.pc \
"
FILES_${PN}-plugin = " \
    ${PLUGINS_TARGET}/qmldir \
    ${PLUGINS_TARGET}/lib*.so \
"
FILES_${PN}-plugin-dbg = " \
    ${PLUGINS_TARGET}/.debug \
    ${PLUGINS_TARGET}/.debug/* \
"

QT_IMPORTS_DIR = "${libdir}/${QT_DIR_NAME}/imports"
# the plugin target needs to be the same as 'target.path' and 'qmldir.path' in ${S}/plugin/plugin.pro
PLUGINS_TARGET = "${QT_IMPORTS_DIR}/MeeGo/Connman"
