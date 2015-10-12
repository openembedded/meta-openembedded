SUMMARY = "A UPnP AV media server and renderer"
DESCRIPTION = "Rygel is a home media solution (UPnP AV MediaServer) that \
allow you to easily share audio, video and pictures to other devices. \
Additionally, media player software may use Rygel to become a MediaRenderer \
that may be controlled remotely by a UPnP or DLNA Controller."
HOMEPAGE = "http://live.gnome.org/Rygel"

LICENSE = "LGPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=5f30f0716dfdd0d91eb439ebec522ec2 \
                    file://src/rygel/rygel-main.vala;endline=25;md5=a4cce4d389da1c1638fe68d07ae9d811"

DEPENDS = "libxml2 glib-2.0 gssdp gupnp gupnp-av gupnp-dlna gstreamer1.0 gstreamer1.0-plugins-base ossp-uuid libgee libsoup-2.4 libmediaart-2.0"
RDEPENDS_${PN} = "gstreamer1.0-plugins-base-playback shared-mime-info"

GNOME_COMPRESS_TYPE = "xz"
SRC_URI[archive.md5sum] = "f182d54913a528bb5b4fb2f291aca0fc"
SRC_URI[archive.sha256sum] = "390740609e34399b886ddb9a8f7eca25055ad72048dfdd869edf02999b1e1d8f"

inherit gnomebase vala

EXTRA_OECONF = "--disable-introspection --disable-tracker-plugin --with-media-engine=gstreamer"

PACKAGECONFIG ?= "external mpris mediathek ruih media-export gst-launch gtk+3"
PACKAGECONFIG[external] = "--enable-external-plugin,--disable-external-plugin"
PACKAGECONFIG[mpris] = "--enable-mpris-plugin,--disable-mpris-plugin"
PACKAGECONFIG[mediathek] = "--enable-mediathek-plugin,--disable-mediathek-plugin"
PACKAGECONFIG[ruih] = "--enable-ruih-plugin,--disable-ruih-plugin"
PACKAGECONFIG[media-export] = "--enable-media-export-plugin,--disable-media-export-plugin,sqlite3"
PACKAGECONFIG[gst-launch] = "--enable-gst-launch-plugin,--disable-gst-launch-plugin"
PACKAGECONFIG[gtk+3] = ",--without-ui,gtk+3"

LIBV = "2.6"

do_install_append() {
       # Remove .la files for loadable modules
       rm -f ${D}/${libdir}/rygel-${LIBV}/engines/*.la
       rm -f ${D}/${libdir}/rygel-${LIBV}/plugins/*.la
}

FILES_${PN} += "${libdir}/rygel-${LIBV}/engines ${datadir}/dbus-1 ${datadir}/icons"
FILES_${PN}-dbg += "${libdir}/rygel-${LIBV}/engines/.debug ${libdir}/rygel-${LIBV}/plugins/.debug"

PACKAGES += "${PN}-meta"
ALLOW_EMPTY_${PN}-meta = "1"

PACKAGES_DYNAMIC = "${PN}-plugin-*"

python populate_packages_prepend () {
    rygel_libdir = d.expand('${libdir}/rygel-${LIBV}')
    postinst = d.getVar('plugin_postinst', True)
    pkgs = []

    pkgs += do_split_packages(d, oe.path.join(rygel_libdir, "plugins"), 'librygel-(.*)\.so$', d.expand('${PN}-plugin-%s'), 'Rygel plugin for %s', postinst=postinst, extra_depends=d.expand('${PN}'))
    pkgs += do_split_packages(d, oe.path.join(rygel_libdir, "plugins"), '(.*)\.plugin$', d.expand('${PN}-plugin-%s'), 'Rygel plugin for %s', postinst=postinst, extra_depends=d.expand('${PN}'))

    metapkg = d.getVar('PN', True) + '-meta'
    d.setVar('RDEPENDS_' + metapkg, ' '.join(pkgs))
}
