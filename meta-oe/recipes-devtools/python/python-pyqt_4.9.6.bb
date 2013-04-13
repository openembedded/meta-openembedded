DESCRIPTION = "Python Qt4 Bindings"
HOMEPAGE = "http://riverbankcomputing.co.uk"
AUTHOR = "Phil Thomson @ riverbank.co.uk"
SECTION = "devel/python"
LICENSE = "GPLv2 & GPLv3 & GPL_EXCEPTION"
LIC_FILES_CHKSUM = "\
    file://GPL_EXCEPTION.TXT;md5=b73b0be471db679533dc94781c14af58 \
    file://GPL_EXCEPTION_ADDENDUM.TXT;md5=c1e04ec2aa0911061005a801abf81e40 \
    file://OPENSOURCE-NOTICE.TXT;md5=6ad9123620cc04a22c394753ad4767d7 \
    file://LICENSE.GPL2;md5=276c6b9cad5f85a3af3534299825feff \
    file://LICENSE.GPL3;md5=eda942b9c6ba7eb0f40fee79e94950d5 \
"

DEPENDS = "sip-native python-sip"
RDEPENDS_${PN} = "python-core"

PYQT_OE_VERSION = "Qt_4_8_3"
PR = "r1"

SRC_URI = "\
    ${SOURCEFORGE_MIRROR}/pyqt/PyQt-x11-gpl-${PV}.tar.gz \
    file://pyqt-generated.patch;apply=no \
"
SRC_URI[md5sum] = "514e1f9597771dc732ba75ba9fa5c6b6"
SRC_URI[sha256sum] = "a350f9e5c6d8062671c0f29bf1a70824719b18175ce8372c29bf7c1eda44b18d"
S = "${WORKDIR}/PyQt-x11-gpl-${PV}"

# arm and mips machines need some extra patches
SRC_URI_append_arm = "\
    file://qreal_float_support.diff \
"

SRC_URI_append_mipsel = "\
    file://qreal_float_support.diff \
"

inherit qt4x11 sip distutils-base

PARALLEL_MAKE = ""

QMAKE_PROFILES = "pyqt.pro"
# NOTE: has to match with MIN(qt version we have in OE, last known Qt version by SIP/PyQt)
EXTRA_SIPTAGS = "-tWS_X11 -t${PYQT_OE_VERSION} -xVendorID -xPyQt_SessionManager -xPyQt_Accessibility"
EXTRA_OEMAKE = " MAKEFLAGS= "

# arm and mips need extra params for the qreal issue
EXTRA_SIPTAGS_append_arm = " -x PyQt_qreal_double"
EXTRA_SIPTAGS_append_mipsel = " -x PyQt_qreal_double" 

SIP_MODULES = "QtCore QtDeclarative QtGui QtNetwork QtSql QtSvg QtXml QtWebKit"
MAKE_MODULES = "qpy ${SIP_MODULES}"

EXTRA_QMAKEVARS_POST += "\
    INCLUDEPATH+=${OE_QMAKE_INCDIR_QT}/Qt \
    INCLUDEPATH+=${STAGING_INCDIR}/${PYTHON_DIR} \
    INCLUDEPATH+=../qpy/QtCore \
    INCLUDEPATH+=../qpy/QtGui \
    INCLUDEPATH+=../qpy/QtDeclarative \
    INCLUDEPATH+=${OE_QMAKE_INCDIR_QT}/QtCore \
    INCLUDEPATH+=${OE_QMAKE_INCDIR_QT}/QtGui \
    INCLUDEPATH+=${OE_QMAKE_INCDIR_QT}/QtDeclarative \
    INCLUDEPATH+=${OE_QMAKE_INCDIR_QT}/QtWebKit \
    INCLUDEPATH+=${OE_QMAKE_INCDIR_QT}/QtNetwork \
"
FIX_QREAL = "\
"

do_generate_prepend() {
    for i in ${FIX_QREAL}; do
        sed -i -e s,qreal,float,g sip/$i
    done
}

do_configure_prepend() {
    printf "TEMPLATE=subdirs\nSUBDIRS=${MAKE_MODULES}\n" >pyqt.pro
    printf "TEMPLATE=subdirs\nSUBDIRS=QtCore QtDeclarative QtGui\n" >qpy/qpy.pro
    ln -sf ./qpycore.pro qpy/QtCore/QtCore.pro
    ln -sf ./qpydeclarative.pro qpy/QtDeclarative/QtDeclarative.pro
    ln -sf ./qpygui.pro qpy/QtGui/QtGui.pro
    echo "INCLUDEPATH+=${S}/QtCore" >>qpy/QtCore/QtCore.pro
    echo "INCLUDEPATH+=${S}/QtGui" >>qpy/QtGui/QtGui.pro
    echo "INCLUDEPATH+=${S}/QtDeclarative" >>qpy/QtDeclarative/QtDeclarative.pro
    echo "LIBS+=-L../qpy/QtGui/ -lqpygui" >>QtGui/QtGui.pro
    echo "LIBS+=-L../qpy/QtCore/ -lqpycore" >>QtCore/QtCore.pro
    echo "LIBS+=-L../qpy/QtDeclarative/ -lqpydeclarative" >>QtDeclarative/QtDeclarative.pro
    # hack for broken generated code (duplicated sipCpp declaration).
    patch -p1 < ${WORKDIR}/pyqt-generated.patch || echo "pyqt-generated.patch failed to apply, probably reexecuting do_configure, ignoring that"
}

do_install() {
    install -d ${D}${libdir}/${PYTHON_DIR}/site-packages/PyQt4
    install -d ${D}${datadir}/sip/qt/
    for module in ${SIP_MODULES}
    do
        install -m 0644 ${S}/sip/${module}/*.sip ${D}${datadir}/sip/qt/
        echo "from PyQt4.${module} import *\n" >> ${D}${libdir}/${PYTHON_DIR}/site-packages/PyQt4/Qt.py
        install -m 0755 ${module}/lib${module}.so ${D}${libdir}/${PYTHON_DIR}/site-packages/PyQt4/${module}.so
    done
    cp -pPR elementtree ${D}${libdir}/${PYTHON_DIR}/site-packages/PyQt4/
    cp __init__.py ${D}${libdir}/${PYTHON_DIR}/site-packages/PyQt4/
}

FILES_${PN} = "${libdir}/${PYTHON_DIR}/site-packages ${datadir}/sip/qt/"
