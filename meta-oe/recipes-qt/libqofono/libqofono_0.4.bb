SUMMARY = "Qt Library for oFono"
HOMEPAGE = "https://github.com/nemomobile/libqofono"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://README;endline=3;md5=8a15bce3921d1238d9a9f23828612947"

SECTION = "qt/lib"

BRANCH = "master"
PV = "0.4+gitr${SRCPV}"
SRCREV = "05055a4b4a579facd007a0a128696030228e0b88"

inherit qt4x11 pkgconfig

RDEPENDS_${PN} = "ofono"
RDEPENDS_${PN}-plugin = "${PN}"

PACKAGES =+ "${PN}-plugin ${PN}-plugin-dbg ${PN}-test"
RRECOMMENDS_${PN} = "${PN}-plugin"

SRC_URI = " \
    git://github.com/nemomobile/${BPN}.git;branch=${BRANCH} \
"
S = "${WORKDIR}/git"

QT_IMPORTS_DIR = "${libdir}/qt4/imports"
PLUGINS_TARGET = "${QT_IMPORTS_DIR}/MeeGo/QOfono"

FILES_${PN} = " \
    ${libdir}/libqofono${SOLIBS} \
"

FILES_${PN}-dev = " \
    ${includedir}/qofono/* \
    ${includedir}/qofono/dbus/* \
    ${libdir}/libqofono${SOLIBSDEV} \
    ${libdir}/libqofono.prl \
    ${libdir}/pkgconfig/qofono.pc \
    ${datadir}/qt4/mkspecs/features/qofono.prf \
"

FILES_${PN}-plugin = " \
    ${PLUGINS_TARGET}/qmldir \
    ${PLUGINS_TARGET}/lib*.so \
"

FILES_${PN}-plugin-dbg = " \
    ${PLUGINS_TARGET}/.debug \
"

FILES_${PN}-test = " \
    /opt/ofonotest/bin/ofonotest \
    /opt/ofonotest/qml/ofonotest \
    ${libdir}/libqofono/tests/tst_qofonotest \
"

FILES_${PN}-dbg += " \
    /opt/ofonotest/bin/.debug \
    ${libdir}/libqofono/tests/.debug \
"

EXTRA_QMAKEVARS_PRE = " \
    PREFIX=${prefix} \
"

do_configure_prepend() {
    # Hack *.pro variables
    find . -iname '*.pro' -exec sed -i -e 's,$$\[QT_INSTALL_IMPORTS\],${QT_IMPORTS_DIR},g' '{}' ';'
    find . -iname '*.pro' -exec sed -i -e 's,$$\[QT_INSTALL_PREFIX\],$$INSTALL_ROOT$$PREFIX,g' '{}' ';'
}

do_install() {
    export INSTALL_ROOT=${D}
    oe_runmake install

    cd ${D}/${datadir}/qt4/mkspecs/features

    sed -i -e '/DEPENDPATH.*/d; /INCLUDEPATH.*/d; /LIBS.*/d' ${D}/${datadir}/qt4/mkspecs/features/qofono.prf

    # to make it work with the SDK
    echo 'DEPENDPATH += $(OE_QMAKE_INCDIR_QT)/../qofono' >> ${D}/${datadir}/qt4/mkspecs/features/qofono.prf
    echo 'INCLUDEPATH += $(OE_QMAKE_INCDIR_QT)/../qofono' >> ${D}/${datadir}/qt4/mkspecs/features/qofono.prf
    echo 'LIBS += -lqofono' >> ${D}/${datadir}/qt4/mkspecs/features/qofono.prf
}
