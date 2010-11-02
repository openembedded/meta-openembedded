DESCRIPTION = "C++ bindings for dbus"
LICENSE = "LGPLv2.1+"
SECTION = "libs"
DEPENDS = "dbus dbus-c++-native expat"
SRCREV = "530a887913d3b0dd7091eabb8eef8e3c279e3bb8"

PE = "1"
PV = "0.5.0+gitr${SRCPV}"

SRC_URI = "git://gitorious.org/dbus-cplusplus/mainline.git;protocol=git"
#           file://fix-linking.patch"
S = "${WORKDIR}/git"

inherit autotools pkgconfig

do_compile_prepend() {
	find . -name "Makefile.am" |xargs sed -i -e 's,$(top_builddir)/tools/dbusxx-xml2cpp,dbusxx-xml2cpp,'
}

FILES_${PN}-dbg += "${bindir}/dbusxx-xml2cpp ${bindir}/dbusxx-introspect"
FILES_${PN}-dev += "${bindir}/.dev"
FILES_${PN} = "${libdir}/*.so.*"

