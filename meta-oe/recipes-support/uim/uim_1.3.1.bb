DESCRIPTION = "A multilingual user input method library"
HOMEPAGE = "http://uim.freedesktop.org/"
LICENSE = "BSD"
SECTION = "inputmethods"

SRC_URI = "http://uim.googlecode.com/files/uim-${PV}.tar.bz2 \
           file://0001-fix-bug-about-stdlib.patch "

SRC_URI_append_class-target = "file://uim-module-manager.patch \
                               file://0001-fix-bug-for-cross-compile.patch"

LIC_FILES_CHKSUM = "file://COPYING;md5=9c372efbeca58ce22017a29c1ebf8bf0"
SRC_URI[md5sum] = "2832e23d4778bbacbfa4b49bf642d667"
SRC_URI[sha256sum] = "ed2cfa15018a4fd2557e875f66fcb3f0b9dabe12fa0700aa2f11cca69c2cb256"

DEPENDS = "anthy fontconfig libxft libxt glib-2.0 ncurses intltool"
DEPENDS_class-target += "gtk+ uim-native takao-fonts"

RDEPENDS_uim = "libuim0 libedit"
RDEPENDS_uim-anthy = "takao-fonts anthy libanthy0"

LEAD_SONAME = "libuim.so.1"

inherit autotools pkgconfig gettext

EXTRA_OECONF += "--disable-emacs \
                 --without-scim \
                 --without-m17nlib \
                 --without-prime \
                 --without-canna \
                 --without-mana \
                 --without-eb"

CONFIGUREOPTS_class-target := "${@d.getVar('CONFIGUREOPTS', True).replace('--disable-silent-rules', ' ')}"

PACKAGES += "uim-xim uim-utils uim-skk uim-gtk2.0 uim-fep uim-common uim-anthy libuim0 libuim-dev"

FILES_libuim0 = "${libdir}/uim/plugin/libuim-custom-enabler.* \
                 ${libdir}/libuim-custom.so.* \
                 ${datadir}/locale/ja/LC_MESSAGES/uim.mo \
                 ${datadir}/locale/fr/LC_MESSAGES/uim.mo \
                 ${datadir}/locale/ko/LC_MESSAGES/uim.mo \
                 ${libdir}/libuim.so.*"

FILES_libuim-dev = "${libdir}/libuim*.a \
                    ${libdir}/libuim*.la \
                    ${libdir}/libuim*.so \
                    ${includedir}/uim \
                    ${libdir}/pkgconfig/uim.pc"

FILES_uim-anthy = "${libdir}/uim/plugin/libuim-anthy.* \
                   ${datadir}/uim/anthy*.scm"

FILES_${PN}-dbg += "${libdir}/*/*/*/.debug ${libdir}/*/*/.debug"
FILES_${PN}-dev += "${libdir}/uim/plugin/*.la"

FILES_uim-utils = "${bindir}/uim-sh \
                   ${bindir}/uim-module-manager \
		   ${libexecdir}/uim-helper-server"

FILES_uim-xim = "${bindir}/uim-xim \
                 ${libexecdir}/uim-candwin-gtk \
                 ${datadir}/man/man1/uim-xim.1 \
                 ${sysconfdir}/X11/xinit/xinput.d/uim*"

FILES_uim-common = "${datadir}/uim/pixmaps/*.png \
                    ${datadir}/uim"

FILES_uim-fep = "${bindir}/uim-fep*"
FILES_uim-gtk2.0 = "${libdir}/gtk-2.0 \
                    ${bindir}/uim-toolbar-gtk* \
                    ${bindir}/uim-*-gtk \
                    ${bindir}/uim-input-pad-ja \
                    ${datadir}/uim/helperdata/uim-dict-ui.xml"

FILES_uim-skk = "${libdir}/uim/plugin/libuim-skk.* \
                 ${datadir}/uim/skk*.scm"

pkg_postinst_uim-anthy() {
    if [ -f /usr/bin/uim-module-manager ]; then
        /usr/bin/uim-module-manager --register anthy --path /etc/uim
    fi
}

pkg_postrm_uim-anthy() {
    if [ -f /usr/bin/uim-module-manager ]; then
        /usr/bin/uim-module-manager --path /etc/uim --unregister anthy
    fi
}

pkg_prerm_uim-anthy() {
    if [ -f /usr/bin/uim-module-manager ]; then
        /usr/bin/uim-module-manager --register anthy --path /etc/uim
    fi
}

pkg_postinst_uim-gtk2.0() {
    gtk-query-immodules-2.0 > /etc/gtk-2.0/gtk.immodules
}

pkg_postinst_uim-skk() {
    if [ -f /usr/bin/uim-module-manager ]; then
        /usr/bin/uim-module-manager --register skk --path /etc/uim
    fi
}

pkg_postrm_uim-skk() {
    if [ -f /usr/bin/uim-module-manager ]; then
        /usr/bin/uim-module-manager --path /etc/uim --unregister skk
    fi
}

pkg_postinst_uim-common() {
    if [ -f /usr/bin/uim-module-manager ]; then
        /usr/bin/uim-module-manager --path /etc/uim --register \
                                    tutcode tcode hangul viqr \
                                    ipa-x-sampa latin byeoru
    fi
}

pkg_prerm_uim-common() {
    if [ -f /usr/bin/uim-module-manager ]; then
        /usr/bin/uim-module-manager --path /etc/uim --register \
        tutcode tcode hangul viqr ipa-x-sampa latin byeoru
    fi
}

BBCLASSEXTEND = "native"
