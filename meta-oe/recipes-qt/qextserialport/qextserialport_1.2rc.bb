SUMMARY = "Qt Ext Serial Port Library"
HOMEPAGE = "http://http://code.google.com/p/qextserialport/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b866a28cda707ec714878bd933f46251"
SECTION = "qt/libs"


# 1.2rc as version will sort lower then 1.2 when it's released
REAL_PV = "1.2rc"
PV = "1.1+${REAL_PV}"

inherit qt4x11 pkgconfig

DEPENDS = "udev"

SRC_URI = " \
    http://qextserialport.googlecode.com/files/qextserialport-${REAL_PV}.zip \
"

SRC_URI[md5sum] = "ffa061edb9f64666468d18402eee6108"
SRC_URI[sha256sum] = "9cbee267aac9830f9944bb1d13872e51fba400aa9afe158e64f0fe265a0176bc"

S = "${WORKDIR}/qextserialport-${REAL_PV}"

FILES_${PN} = "${libdir}/libqextserialport${SOLIBS}"

FILES_${PN}-dev = " \
    ${libdir}/libqextserialport${SOLIBSDEV} \
    ${libdir}/libqextserialport.prl \
    ${includedir}/QtExtSerialPort/*.h \
    ${datadir}/qt4/mkspecs/features/extserialport.prf \
"

FILES_${PN}-dbg += " \
    ${libdir}/.debug/libqextserialport.so* \
"

do_configure_prepend() {
    # based on the documentation, this line make sure we use udev in linux
    cd ${S} && echo "linux*:CONFIG += qesp_linux_udev" > .qmake.cache

    # Hacking hardcoded qmake variables
    find *.pro -exec sed -i -e 's,$$\[QT_INSTALL_HEADERS\],${includedir},g' '{}' ';'
    find *.pro -exec sed -i -e 's,$$\[QT_INSTALL_LIBS\],${libdir},g' '{}' ';'
    find *.pro -exec sed -i -e 's,$$\[QMAKE_MKSPECS\],${datadir}/qt4/mkspecs/,g' '{}' ';'
}

do_install() {
    export INSTALL_ROOT=${D}
    oe_runmake install

    # This is necessary to make it work with the qt based SDK
    cd ${D}/${datadir}/qt4/mkspecs/features && sed -i -e "s|${STAGING_INCDIR_NATIVE}/qt4|\$(OE_QMAKE_INCDIR_QT)/..|" ./extserialport.prf
}
