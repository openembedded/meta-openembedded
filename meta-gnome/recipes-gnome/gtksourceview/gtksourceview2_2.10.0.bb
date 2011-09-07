DESCRIPTION = "Portable C library for multiline text editing"
HOMEPAGE = "http://projects.gnome.org/gtksourceview/"
LICENSE = "GPLv2"
DEPENDS = "gtk+ libgnomeprint virtual/gettext"

LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

PNAME = "gtksourceview"

S = "${WORKDIR}/${PNAME}-${PV}"

inherit gnome lib_package

# overrule SRC_URI from gnome.conf
SRC_URI = "${GNOME_MIRROR}/${PNAME}/${@gnome_verdir("${PV}")}/${PNAME}-${PV}.tar.bz2;name=archive"
SRC_URI[archive.md5sum] = "04279db5d4fda41a35bf3d9aafa3a6c1"
SRC_URI[archive.sha256sum] = "a72484ff661d3515130911b59f78a88afc1344421431e05e99e9dab791be948f"

SRC_URI += " \
           file://gtk-doc.make \
"

do_configure_prepend() {
    cp ${WORKDIR}/gtk-doc.make ${S}/
    sed -i -e s:docs::g ${S}/Makefile.am
    echo "EXTRA_DIST = version.xml" > gnome-doc-utils.make
}

FILES_${PN} += " ${datadir}/gtksourceview-2.0"
