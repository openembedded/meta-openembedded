DESCRIPTION = "A virtual keyboard for touch-screen based user interfaces"
HOMEPAGE = "https://wiki.maliit.org/Main_Page"

LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://LICENSE.LGPL;md5=5c917f6ce94ceb8d8d5e16e2fca5b9ad"

inherit autotools qt4x11


SRC_URI = "git://gitorious.org/maliit/maliit-framework.git;branch=master \
    file://0001-Fix-MALIIT_INSTALL_PRF-to-allow-the-build-with-opene.patch \
    file://0001-Fix-QT_IM_PLUGIN_PATH-to-allow-openembedded-to-build.patch \
    "

SRCREV = "750842dec74a9b17dca91ef779c4fc5a43c4d9dc"
PV = "0.92.3+git${SRCPV}"


PACKAGES =+ "\
    ${PN}-gtk \
    "

# Maliit needs Qt configured with -accessibility, a patch for that was already sent and will get merged in post 1.2.
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
    "

do_install() {
    cd ${S} && (INSTALL_ROOT=${D} oe_runmake install)

    #Fix absolute paths
    cd ${D}/${datadir}/qt4/mkspecs/features && sed -i -e "s|/usr|${STAGING_DIR_TARGET}${prefix}|" ./maliit-framework.prf
    cd ${D}/${datadir}/qt4/mkspecs/features && sed -i -e "s|/usr|${STAGING_DIR_TARGET}${prefix}|" ./maliit-plugins.prf
}



# Update the inputmethod modules in gtk
pkg_postinst_${PN}-gtk() {
if [ "x$D" != "x" ]; then
    exit 1
fi
gtk-query-immodules-2.0 > /etc/gtk-2.0/gtk.immodules
}

pkg_postrm_${PN}-gtk() {
if [ "x$D" != "x" ]; then
    exit 1
fi
gtk-query-immodules-2.0 > /etc/gtk-2.0/gtk.immodules
}

S= "${WORKDIR}/git"
