DESCRIPTION="Gnome Office Library"

LICENSE="GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=fa1348b178f4a7322170203c6c36f4c3"

DEPENDS = "libxml-parser-perl-native glib-2.0 gtk+ pango cairo libgsf libpcre libxml2 libart-lgpl"

inherit gnome pkgconfig perlnative

SRC_URI += " file://c99math.patch \
             file://nodolt.patch "

SRC_URI[archive.md5sum] = "b4c924457163e02daf8a8d2428f51d10"
SRC_URI[archive.sha256sum] = "dd8caef5fefffbc53938fa619de9f58e7c4dc71a1803de134065d42138a68c06"

FILES_${PN}-dbg += "${libdir}/goffice/${PV}/plugins/*/.debug"

RRECOMMENDS_${PN} = " \
  goffice-plugin-plot-barcol \
  goffice-plugin-plot-distrib \
  goffice-plugin-plot-pie \
  goffice-plugin-plot-radar \
  goffice-plugin-plot-surface \
  goffice-plugin-plot-xy \
  goffice-plugin-reg-linear \
  goffice-plugin-reg-logfit \
  goffice-plugin-smoothing \
"

FILES_${PN} = "${bindir}/* ${sbindir}/* ${libexecdir}/* ${libdir}/lib*${SOLIBS} \
            ${sysconfdir} ${sharedstatedir} ${localstatedir} \
            ${base_bindir}/* ${base_sbindir}/* \
            ${base_libdir}/*${SOLIBS} \
            ${datadir}/${PN} \
            ${datadir}/pixmaps ${datadir}/applications \
            ${datadir}/idl ${datadir}/omf ${datadir}/sounds \
            ${libdir}/bonobo/servers"

PACKAGES_DYNAMIC = "goffice-plugin-*"

python populate_packages_prepend () {
        goffice_libdir = bb.data.expand('${libdir}/goffice/${PV}/plugins/', d)

        do_split_packages(d, goffice_libdir, '(.*)', 'goffice-plugin-%s', 'Goffice plugin for %s', allow_dirs=True)
}

