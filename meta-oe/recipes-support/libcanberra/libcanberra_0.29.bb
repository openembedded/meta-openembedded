SUMMARY = "Implementation of XDG Sound Theme and Name Specifications"
DESCRIPTION = "Libcanberra is an implementation of the XDG Sound Theme and Name Specifications, for generating event sounds on free desktops."
LICENSE = "LGPLv2.1+"
LIC_FILES_CHKSUM = "file://LGPL;md5=2d5025d4aa3495befef8f17206a5b0a1 \
                    file://src/canberra.h;beginline=7;endline=24;md5=c616c687cf8da540a14f917e0d23ab03"

DEPENDS = "gstreamer gtk+ gtk+3 libtool libvorbis"

inherit autotools gtk-doc

SRC_URI = " \
    http://0pointer.de/lennart/projects/${BPN}/${BPN}-${PV}.tar.xz \
    file://0001-build-gtk-and-gtk3-version-for-canberra_gtk_play.patch \
"
SRC_URI[md5sum] = "2594093a5d61047bd9cc87e955f86df8"
SRC_URI[sha256sum] = "127a5ef07805856d63758e5180ebfb241d1f80094fd301c287591a15b8cfcd72"

EXTRA_OECONF = "\
    --enable-null \
    --enable-gstreamer \
    --disable-oss \
    --enable-gtk \
    --enable-gtk3 \
    --disable-tdb \
    --disable-lynx \
"

PACKAGECONFIG ??= "${@base_contains('DISTRO_FEATURES','alsa','alsa','',d)} \
                   ${@base_contains('DISTRO_FEATURES','pulseaudio','pulseaudio','',d)} \
"
PACKAGECONFIG[alsa] = "--enable-alsa, --disable-alsa, alsa-lib"
PACKAGECONFIG[pulseaudio] = "--enable-pulse, --disable-pulse, pulseaudio"

python populate_packages_prepend() {
    plugindir = d.expand('${libdir}/${BPN}-${PV}/')
    do_split_packages(d, plugindir, '^libcanberra-(.*)\.so$', 'libcanberra-%s', '%s support library', extra_depends='' )
    do_split_packages(d, plugindir, '^libcanberra-(.*)\.la$', 'libcanberra-%s', '%s support library', extra_depends='' )
}

PACKAGES =+ "${PN}-gnome ${PN}-gtk2 ${PN}-gtk3 ${PN}-systemd"
PACKAGES_DYNAMIC += "^libcanberra-.*"

FILES_${PN} = "${bindir}/ ${libdir}/${BPN}.so.*"

FILES_${PN}-dev += "${datadir}/vala/vapi ${libdir}/*/modules/*.la ${libdir}/*/*.la"

FILES_${PN}-dbg += "${libdir}/${BPN}-${PV}/.debug ${libdir}/gtk-*/modules/.debug"

FILES_${PN}-gtk2 = "${libdir}/${BPN}-gtk.so.* \
                    ${libdir}/gtk-2.0/modules/*.so \
                    ${bindir}/canberra-gtk-play"

# -gtk3 ships a symlink to a .so
INSANE_SKIP_${PN}-gtk3 = "dev-so"
FILES_${PN}-gtk3 = "${libdir}/${BPN}-gtk3.so.* \
                    ${libdir}/gtk-3.0/modules/*.so \
                    ${bindir}/canberra-gtk3-play"

FILES_${PN}-gnome = "${libdir}/gnome-settings-daemon-3.0/ \
                     ${datadir}/gdm/ ${datadir}/gnome/"

FILES_${PN}-systemd = "${systemd_unitdir}/system/*.service"
