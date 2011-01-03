QTVERSION="4.4.3"
FILESDIR += "${@os.path.dirname(bb.data.getVar('FILE',d,1))}/qmake2"

BBCLASSEXTEND = "native sdk"

require ${PN}.inc

SRC_URI += "file://qmake-hack.diff"

do_install() {
    install -d ${D}/${bindir}
    install -m 0755 bin/qmake ${D}/${bindir}/qmake2
    install -m 0755 bin/qmake ${D}/${bindir}/qmake-qt4
    install -d ${D}/${datadir}/qt4
    install -d ${D}/${datadir}/qtopia

    script="${D}/${datadir}/qtopia/environment-setup"
    touch $script
    echo 'export QT_DIR_NAME=qtopia' >> $script
    echo 'export QT_LIBINFIX=E' >> $script
    echo 'export OE_QMAKE_AR=ar' >> $script
    echo 'export OE_QMAKE_CC=gcc' >> $script
    echo 'export OE_QMAKE_CXX=g++' >> $script
    echo 'export OE_QMAKE_LINK=g++' >> $script
    echo 'export OE_QMAKE_LIBDIR_QT=${libdir}' >> $script
    echo 'export OE_QMAKE_INCDIR_QT=${includedir}/qtopia' >> $script
    echo 'export OE_QMAKE_MOC=${bindir}/moc' >> $script
    echo 'export OE_QMAKE_UIC=${bindir}/uic' >> $script
    echo 'export OE_QMAKE_UIC3=${bindir}/uic3' >> $script
    echo 'export OE_QMAKE_RCC=${bindir}/rcc' >> $script
    echo 'export OE_QMAKE_QDBUSCPP2XML=${bindir}/qdbuscpp2xml' >> $script
    echo 'export OE_QMAKE_QDBUSXML2CPP=${bindir}/qdbusxml2cpp' >> $script
    echo 'export OE_QMAKE_QT_CONFIG=${datadir}/qtopia/mkspecs/qconfig.pri' >> $script
    echo 'export QMAKESPEC=${datadir}/qtopia/mkspecs/linux-g++' >> $script

    script="${D}/${datadir}/qt4/environment-setup"
    touch $script
    echo 'export OE_QMAKE_AR=ar' >> $script
    echo 'export OE_QMAKE_CC=gcc' >> $script
    echo 'export OE_QMAKE_CXX=g++' >> $script
    echo 'export OE_QMAKE_LINK=g++' >> $script
    echo 'export OE_QMAKE_LIBDIR_QT=${libdir}' >> $script
    echo 'export OE_QMAKE_INCDIR_QT=${includedir}/qt4' >> $script
    echo 'export OE_QMAKE_MOC=${bindir}/moc' >> $script
    echo 'export OE_QMAKE_UIC=${bindir}/uic' >> $script
    echo 'export OE_QMAKE_UIC3=${bindir}/uic3' >> $script
    echo 'export OE_QMAKE_RCC=${bindir}/rcc' >> $script
    echo 'export OE_QMAKE_QDBUSCPP2XML=${bindir}/qdbuscpp2xml' >> $script
    echo 'export OE_QMAKE_QDBUSXML2CPP=${bindir}/qdbusxml2cpp' >> $script
    echo 'export OE_QMAKE_QT_CONFIG=${datadir}/qt4/mkspecs/qconfig.pri' >> $script
    echo 'export QMAKESPEC=${datadir}/qt4/mkspecs/linux-g++' >> $script

    chmod 0755 ${D}${datadir}/qt*/environment-setup
}

FILES_${PN} += "${datadir}/qt*/environment-setup"


SRC_URI[md5sum] = "9a639aec44a1e4c70040117183d247a3"
SRC_URI[sha256sum] = "05d06b93f95092f1318634fca24f0c2d0a1252c9f1dc2fbb427b07e8ecbb4f39"
