SUMMARY = "D-Bus implementation in C++"
HOMEPAGE = "https://dbus-cxx.github.io/"
BUGTRACKER = "https://github.com/dbus-cxx/dbus-cxx/issues"
SECTION = "base"
LICENSE = "LGPL-3.0-or-later | BSD-3-Clause"
LIC_FILES_CHKSUM = "file://COPYING;md5=24594f493407a4cd401ce9794e0b9308"

SRC_URI = "git://github.com/dbus-cxx/dbus-cxx.git;branch=master;protocol=https"
SRCREV = "f2637e726207ecfbdaaf02744e1b36e54f929c9d"

DEPENDS = "\
	dbus \
	libsigc++-3 \
"

RDEPENDS:${PN} = "\
	dbus \
	libsigc++-3 \
"

S = "${WORKDIR}/git"

inherit pkgconfig cmake

OECMAKE_FIND_ROOT_PATH_MODE_PROGRAM = "BOTH"
EXTRA_OECMAKE = "-DBUILD_TESTING=OFF -DTOOLS_BUNDLED_CPPGENERATE=OFF"

PACKAGECONFIG ??= ""
PACKAGECONFIG:class-native ?= "tools"
PACKAGECONFIG:class-nativesdk ?= "tools"

PACKAGECONFIG[tools] = "-DENABLE_TOOLS=ON,-DENABLE_TOOS=OFF,popt cppgenerate"
PACKAGECONFIG[glib] = "-DENABLE_GLIB_SUPPORT=ON,-DENABLE_GLIB_SUPPORT=OFF,glib-2.0"
PACKAGECONFIG[uv] = "-DENABLE_UV_SUPPORT=ON,-DENABLE_UV_SUPPORT=OFF,libuv"

PACKAGES =+ " ${PN}-tools ${PN}-glib ${PN}-uv ${PN}-glib-dev ${PN}-uv-dev "
FILES:${PN}-tools = "${bindir}/dbus-cxx-xml2cpp ${bindir}/dbus-cxx-introspect"
FILES:${PN}-glib = "${libdir}/libdbus-cxx-glib.so.* "
FILES:${PN}-glib-dev = "${includedir}/dbus-cxx-glib-2.0/* \
  ${libdir}/pkgconfig/dbus-cxx-glib-2.0.pc \
  ${libdir}/libdbus-cxx-glib.so \
  "
FILES:${PN}-uv = "${libdir}/libdbus-cxx-uv.so.* "
FILES:${PN}-uv-dev = "${includedir}/dbus-cxx-uv-2.0/* \
  ${libdir}/pkgconfig/dbus-cxx-uv-2.0.pc\
  ${libdir}/libdbus-cxx-uv.so \
  "

BBCLASSEXTEND="native nativesdk"
