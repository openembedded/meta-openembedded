# Copyright (C) 2022 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Gstreamer editing services"
HOMEPAGE = "http://cgit.freedesktop.org/gstreamer/gst-editing-services/"

LICENSE = "GPL-2.0-or-later AND LGPL-2.1-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=69333daa044cb77e486cc36129f7a770 \
                    file://COPYING.LIB;md5=69333daa044cb77e486cc36129f7a770"

DEPENDS = "flex-native gstreamer1.0 gstreamer1.0-plugins-base gstreamer1.0-plugins-bad python3-pygobject"

REQUIRED_DISTRO_FEATURES = "gobject-introspection-data"
GIR_MESON_OPTION = ""

inherit meson pkgconfig upstream-version-is-even gobject-introspection features_check bash-completion

EXTRA_OEMESON = "-Dvalidate=disabled"

SRC_URI = "http://gstreamer.freedesktop.org/src/gst-editing-services/gst-editing-services-${PV}.tar.xz"
SRC_URI[sha256sum] = "23caf8439ba063091da226e7a1774915848105ff47ebdbe7773fffa21a094692"

PACKAGES += "gst-validate-launcher libges"

FILES:gst-validate-launcher = "${nonarch_libdir}/gst-validate-launcher ${datadir}/gstreamer-1.0/validate"
FILES:libges = "${libdir}/gstreamer-1.0/*.so"

FILES:${PN} += "/usr/lib/python${PYTHON_BASEVERSION}"
