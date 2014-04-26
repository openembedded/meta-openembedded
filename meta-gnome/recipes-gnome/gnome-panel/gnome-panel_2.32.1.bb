SUMMARY = "GNOME panel"
LICENSE = "GPLv2 & LGPLv2 & GFDLv1.1"

LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
                    file://COPYING-DOCS;md5=c9211dab3ae61e580f48432020784324 \
                    file://COPYING.LIB;md5=5f30f0716dfdd0d91eb439ebec522ec2"

SECTION = "x11/gnome"

PR = "r7"

DEPENDS = "gnome-doc-utils-native gconf glib-2.0 gnome-desktop gtk+ pango libwnck gnome-menus cairo libgweather dbus-glib librsvg libcanberra" 

inherit gtk-doc gnome autotools-brokensep gettext pkgconfig gconf

SRCREV = "6a364b6a4a9beed3da9ca6f5b0dac81eb99dea2a"
SRC_URI = "git://git.gnome.org/gnome-panel;branch=gnome-2-32"

S = "${WORKDIR}/git"

EXTRA_OECONF = "--disable-scrollkeeper --disable-eds --enable-bonobo=no --with-in-process-applets=none"

do_configure_prepend() {
    gnome-doc-prepare --automake
    sed -i -e s:help:: ${S}/Makefile.am
    sed -i -e s:^#!@PYTHON@:#!/usr/bin/python: ${S}/gnome-panel/gnome-panel-add.in
}

PACKAGES =+ "libpanel-applet"
FILES_libpanel-applet = "${libdir}/libpanel-applet-*.so.*"

FILES_${PN} =+ "${datadir}/gnome* \
                ${datadir}/dbus-1 \
                ${datadir}/icons \
                ${datadir}/PolicyKit \
                ${libdir}/bonobo \
"
