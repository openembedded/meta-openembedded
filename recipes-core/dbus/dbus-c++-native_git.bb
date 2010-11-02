require dbus-c++_git.bb
inherit native

FILESDIR = "${@os.path.dirname(bb.data.getVar('FILE',d,1))}/dbus-c++"
# actually dbus-native and expat-native, but even the bearest build machine should have that nowadays...
DEPENDS = ""

do_compile() {
	oe_runmake -C src libdbus-c++-1.la
	oe_runmake -C tools dbusxx-xml2cpp
	install -m 0755 tools/dbusxx-xml2cpp ${STAGING_BINDIR_NATIVE}
}

do_install() {
	:
}

