DESCRIPTION = "A virtual keyboard for touch-screen based user interfaces"
HOMEPAGE = "https://wiki.maliit.org/Main_Page"

LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://LICENSE.LGPL;md5=5c917f6ce94ceb8d8d5e16e2fca5b9ad"

inherit autotools qt4x11 gtk-immodules-cache


SRC_URI = "git://gitorious.org/maliit/maliit-framework.git;branch=master \
    file://0001-Fix-MALIIT_INSTALL_PRF-to-allow-the-build-with-opene.patch \
    file://0001-Fix-QT_IM_PLUGIN_PATH-to-allow-openembedded-to-build.patch \
    "

SRCREV = "750842dec74a9b17dca91ef779c4fc5a43c4d9dc"
PV = "0.92.3+git${SRCPV}"


PACKAGES =+ "${PN}-gtk"
GTKIMMODULES_PACKAGES = "${PN}-gtk"

RDEPENDS_${PN} = "qt4-plugin-inputmethod-imsw-multi libqtsvg4"

RRECOMMENDS_${PN} = "maliit-plugins"


FILES_${PN} += "\
    ${libdir}/maliit/plugins-*/factories/libmaliit-plugins-quick-factory-*.so \
    ${libdir}/qt4/plugins/inputmethods/*.so \
    "

FILES_${PN}-dbg += "\
    ${libdir}/maliit-framework-tests \
    ${libdir}/gtk-*/*/immodules/.debug \
    ${libdir}/maliit/plugins-*/factories/.debug \
    ${libdir}/qt4/plugins/.debug \
    ${libdir}/qt4/plugins/inputmethods/.debug \
    "

FILES_${PN}-dev += "${datadir}/qt4"

FILES_${PN}-gtk +="\
    ${bindir}/maliit-exampleapp-gtk* \
    \
    ${libdir}/gtk-*/*/immodules/libim-maliit.so\
    "

EXTRA_QMAKEVARS_PRE = "\
    PREFIX=${prefix} \
    LIBDIR=${libdir} \
    QT_IM_PLUGIN_PATH=${libdir}/qt4/plugins/inputmethods \
    MALIIT_INSTALL_PRF=${datadir}/qt4/mkspecs/features \
    SCHEMADIR=${sysconfdir}/gconf/schemas \
    CONFIG+=disable-gconf \
    CONFIG+=disable-gtk-cache-update \
    CONFIG+=local-install \
    CONFIG+=nosdk \
    "

EXTRA_OEMAKE += "INSTALL_ROOT=${D}"

do_install_append() {
    #Fix absolute paths
    sed -i -e "s|/usr|${STAGING_DIR_TARGET}${prefix}|" ${D}/${datadir}/qt4/mkspecs/features/maliit-framework.prf
    sed -i -e "s|/usr|${STAGING_DIR_TARGET}${prefix}|" ${D}/${datadir}/qt4/mkspecs/features/maliit-plugins.prf
}

S= "${WORKDIR}/git"
