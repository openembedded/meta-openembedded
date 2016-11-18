SUMMARY = "DBus-C++ Library"
DESCRIPTION = "DBus-c++ attempts to provide a C++ API for D-BUS. The library has a glib and an Ecore mainloop integration. It also offers an optional own main loop."
HOMEPAGE = "http://dbus-cplusplus.sourceforge.net"
SECTION = "base"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=fbc093901857fcd118f065f900982c24"
DEPENDS = "dbus glib-2.0 libpcre"

SRC_URI = "${SOURCEFORGE_MIRROR}/project/dbus-cplusplus/dbus-c++/${PV}/${BPN}-${PV}.tar.gz \
		   file://fix-missing-unistd.h-include.patch \
		   file://remove-CXX_FOR_BUILD-stuff.patch"
SRC_URI[md5sum] = "e752116f523fa88ef041e63d3dee4de2"
SRC_URI[sha256sum] = "bc11ac297b3cb010be904c72789695543ee3fdf3d75cdc8225fd371385af4e61"

EXTRA_OECONF = "--disable-ecore --disable-examples --disable-tests"

inherit autotools pkgconfig

PACKAGES += "${PN}-tools"

FILES_${PN} = "${libdir}"
FILES_${PN}-tools = "${bindir}"

BBCLASSEXTEND = "native"
