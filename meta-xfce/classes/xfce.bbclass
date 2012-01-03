HOMEPAGE = "http://www.xfce.org"
SRC_URI = "http://archive.xfce.org/src/xfce/${BPN}/${@'${PV}'[0:3]}/${BPN}-${PV}.tar.bz2"

inherit autotools gettext gtk-icon-cache pkgconfig

FILES_${PN} += "${datadir}/icons/* ${datadir}/applications/* ${libdir}/xfce4/modules/*.so*"
FILES_${PN}-doc += "${datadir}/xfce4/doc"

FILES_${PN}-dev += "${libdir}/xfce4/*/*.la"
FILES_${PN}-dev += "${libdir}/xfce4/*/*/*.la"
FILES_${PN}-staticdev += "${libdir}/xfce4/*/*.a"
FILES_${PN}-staticdev += "${libdir}/xfce4/*/*/*.a"
FILES_${PN}-dbg += "${libdir}/xfce4/*/.debug"
FILES_${PN}-dbg += "${libdir}/xfce4/*/*/.debug"

