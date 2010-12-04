DESCRIPTION = "Plugins for GStreamer"
HOMEPAGE = "http://gstreamer.freedesktop.org/"
BUGTRACKER = "https://bugzilla.gnome.org/enter_bug.cgi?product=Gstreamer"
SECTION = "multimedia"
PRIORITY = "optional"
DEPENDS = "gstreamer"

inherit autotools pkgconfig

SRC_URI = "http://gstreamer.freedesktop.org/src/${PN}/${PN}-${PV}.tar.bz2"

EXTRA_OECONF = "--disable-valgrind --disable-debug --disable-examples "

acpaths = "-I ${S}/common/m4 -I ${S}/m4"

LIBV = "0.10"
#FILES_${PN} += "${libdir}/gstreamer-${LIBV}/*.so"
#FILES_${PN}-dev += "${libdir}/gstreamer-${LIBV}/*.la ${libdir}/gstreamer-${LIBV}/*.a"
FILES_${PN}-dbg += "${libdir}/gstreamer-${LIBV}/.debug"

PACKAGES_DYNAMIC = "${PN}-*"

# orc.m4 calls pkg-config ----variable=orcc orc-0.4 to get the path to orcc,
# resulting in /usr/bin/orcc. Force it to use the staged orcc.
do_configure_append() {
 	for i in $(find ${S} -name "Makefile") ; do
		sed -i -e s:${bindir}/orcc:${STAGING_BINDIR_NATIVE}/orcc:g $i
 	done
}

python populate_packages_prepend () {
	gst_libdir = bb.data.expand('${libdir}/gstreamer-${LIBV}', d)
	postinst = bb.data.getVar('plugin_postinst', d, 1)

#	Can't package separate debug packages yet
#	do_split_packages(d, gst_libdir, '\.debug/libgst(.*)\.so$', bb.data.expand('${PN}-%s-dbg', d), 'GStreamer plugin for %s (with debugging symbols)', recursive=True, extra_depends=bb.data.expand('${PN}-dbg', d), match_path=True)
	do_split_packages(d, gst_libdir, 'libgst(.*)\.so$', bb.data.expand('${PN}-%s', d), 'GStreamer plugin for %s', postinst=postinst, extra_depends=bb.data.expand('${PN}',d))
	do_split_packages(d, gst_libdir, 'libgst(.*)\.l?a$', bb.data.expand('${PN}-%s-dev', d), 'GStreamer plugin for %s (development files)', extra_depends=bb.data.expand('${PN}-dev',d))
}

ALLOW_EMPTY = "1"
