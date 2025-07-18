DESCRIPTION = "Gstreamer Daemon"
SUMMARY = "GStreamer framework for controlling audio and video streaming using TCP connection messages"
HOMEPAGE = "https://developer.ridgerun.com/wiki/index.php?title=Gstd-1.0"
SECTION = "multimedia"
LICENSE = "LGPL-2.1-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=c71b653a0f608a58cdc5693ae57126bc"

DEPENDS = "gstreamer1.0 gstreamer1.0-plugins-base gstreamer1.0-plugins-bad gstreamer1.0-rtsp-server json-glib libdaemon libsoup-2.4 jansson"

SRCBRANCH ?= "master"
SRCREV = "dac03d5e4102080a8f3ef274a6a6583455962095"
SRC_URI = "git://git@github.com/RidgeRun/gstd-1.x.git;protocol=https;branch=${SRCBRANCH} \
           file://0001-gstd-Remove-redundant-rpaths.patch"

# Remove the +really when upstream version is > 1.0
PV = "1.0+really0.15.2"

GTKDOC_MESON_OPTION = "enable-gtk-doc"

# Documentation doesn't currently build:
# gtkdoc-mkhtml: error: unrecognized arguments: ../gstd-docs.xml
GTKDOC_ENABLED = "False"

inherit meson pkgconfig gettext gtk-doc python3native python3-dir python3targetconfig

do_install:append() {
        rmdir ${D}${exec_prefix}${localstatedir}/run/${BPN} ${D}${exec_prefix}${localstatedir}/run \
              ${D}${exec_prefix}${localstatedir}/log/${BPN} ${D}${exec_prefix}${localstatedir}/log \
              ${D}${exec_prefix}${localstatedir}
        rm -f ${D}${bindir}/gst-client ${D}${bindir}/gstd-client
        if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
                install -d ${D}${sysconfdir}/tmpfiles.d
                echo "d /run/${BPN} - - - -" \
                > ${D}${sysconfdir}/tmpfiles.d/${BPN}.conf
                echo "d ${localstatedir}/log/${BPN} 0755 root root -" \
                >> ${D}${sysconfdir}/tmpfiles.d/${BPN}.conf
        fi
        ln -sf gst-client-1.0 ${D}${bindir}/gst-client
        ln -sf gst-client-1.0 ${D}${bindir}/gstd-client
}
PACKAGES =+ "${PN}-python"

FILES:${PN} += "${systemd_user_unitdir}"
FILES:${PN}-python = "${PYTHON_SITEPACKAGES_DIR}/*"
