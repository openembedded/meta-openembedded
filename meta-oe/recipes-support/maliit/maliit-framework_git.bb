SUMMARY = "A virtual keyboard for touch-screen based user interfaces"
HOMEPAGE = "https://wiki.maliit.org/Main_Page"

LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://LICENSE.LGPL;md5=5c917f6ce94ceb8d8d5e16e2fca5b9ad"

inherit autotools qt4x11 gtk-immodules-cache

PNBLACKLIST[maliit-framework] ?= "BROKEN: Wasn't updated to work with B!=S, maybe incorrectly inherits autotools"

DEPENDS = "libxcomposite dbus gtk+3 gtk+"

SRC_URI = "git://github.com/maliit/framework.git;branch=master \
    file://0001-Fix-MALIIT_INSTALL_PRF-to-allow-the-build-with-opene.patch \
    file://0001-Fix-QT_IM_PLUGIN_PATH-to-allow-openembedded-to-build.patch \
    file://0001-Link-to-libmaliit-1-0-in-inputcontext-plugin.patch \
    file://0002-ut_maliit_glib_settings-fix-build-for-glib-2.36.patch \
    file://maliit-server.desktop \
"

SRCREV = "750842dec74a9b17dca91ef779c4fc5a43c4d9dc"
PV = "0.92.3+git${SRCPV}"
PR = "r1"


PACKAGES =+ "${PN}-gtk"
GTKIMMODULES_PACKAGES = "${PN}-gtk"

RDEPENDS_${PN} = "qt4-plugin-inputmethod-imsw-multi libqtsvg4"

RRECOMMENDS_${PN} = "maliit-plugins"


FILES_${PN} += "\
    ${libdir}/maliit/plugins-*/factories/libmaliit-plugins-quick-factory-*.so \
    ${libdir}/qt4/plugins/inputmethods/*.so \
    ${datadir}/applications/maliit-server.desktop \
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

    install -d ${D}${datadir}/applications
    install -m 644 ${WORKDIR}/maliit-server.desktop ${D}${datadir}/applications
}

pkg_postinst_${PN} () {
#!/bin/sh
# should run online
if [ "x$D" != "x" ]; then
    exit 1
fi
echo "export QT_IM_MODULE=Maliit" >> /etc/xprofile
ln -s /usr/share/applications/maliit-server.desktop /etc/xdg/autostart/maliit-server.desktop
}

pkg_postrm_${PN} () {
#!/bin/sh
# should run online
if [ "x$D" = "x" ]; then
    exit 1
fi
if [ -e "/etc/xprofile" ]; then
    sed -i -e "g|export QT_IM_MODULE=Maliit|d" /etc/xprofile
fi
rm -f /etc/xdg/autostart/maliit-server.desktop
}

S = "${WORKDIR}/git"
