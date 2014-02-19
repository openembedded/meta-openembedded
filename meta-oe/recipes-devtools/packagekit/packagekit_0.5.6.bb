SUMMARY = "PackageKit package management abstraction"
SECTION = "libs"
LICENSE = "GPL-2.0+"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"
DEPENDS = "gtk+ python polkit dbus dbus-glib glib-2.0 sqlite3 opkg intltool intltool-native"
RDEPENDS_${PN} = "opkg"

inherit gnome pythonnative

SRC_URI = "http://www.packagekit.org/releases/PackageKit-${PV}.tar.gz;name=archive \
           file://configurefix.patch \
           file://opkgfixes.patch \
           file://0001-Don-t-call-deprecated-glib-functions-and-use-the-new.patch \
          "

SRC_URI[archive.md5sum] = "6c8d9c48e21b82abeea15c3fd5066242"
SRC_URI[archive.sha256sum] = "0eafd1be5516a41ebc0f0c3acff0b0763da105a4178b5eee0ff16d66ccd04408"

S = "${WORKDIR}/PackageKit-${PV}"

PACKAGECONFIG ??= ""
PACKAGECONFIG[service-packs] = "--enable-service-packs,--disable-service-packs,libarchive"

EXTRA_OECONF = "--with-security-framework=dummy \
                --with-default-backend=opkg \
                --enable-opkg \
                --disable-tests \
                --disable-ruck \
                --disable-qt \
                --disable-gstreamer-plugin \
                --disable-local  \
                --disable-networkmanager \
                ac_cv_path_XMLTO=no \
"

#do_configure_prepend() {
#    mkdir -p m4
#    echo "EXTRA_DIST=" > gtk-doc.make
#}

do_configure_append() {
    for i in $(find . -name Makefile) ; do
        sed -i -e s:${STAGING_DIR_NATIVE}::g \
               -e s:${bindir}/mkdir:${STAGING_BINDIR_NATIVE}/mkdir:g \
               -e s:/usr/bin/intltool-merge:${STAGING_BINDIR_NATIVE}/intltool-merge:g \
               $i
    done
}

PACKAGES =+ "${PN}-website"
FILES_${PN}-website = "${datadir}/PackageKit/website"

PACKAGES =+ "${PN}-python"
FILES_${PN}-python = "${libdir}/python*"

PACKAGES =+ "${PN}-gtkmodule"
FILES_${PN}-gtkmodule = "${libdir}/gtk-2.0/*/*.so"

FILES_${PN} += "${libdir}/packagekit-backend/*.so ${libdir}/pm-utils ${datadir}/dbus-1/system-services/ ${datadir}/PolicyKit ${datadir}/PackageKit"
FILES_${PN}-dbg += "${libdir}/packagekit-backend/.debug/*.so ${libdir}/gtk-2.0/*/.debug"
FILES_${PN}-dev += "${libdir}/packagekit-backend/*.la ${libdir}/gtk-2.0/*/*.la"
FILES_${PN}-staticdev += "${libdir}/packagekit-backend/*.a ${libdir}/gtk-2.0/*/*.a"
