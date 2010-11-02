DESCRIPTION = "GLib bindings for the dbus message bus system"
HOMEPAGE = "http://www.freedesktop.org/Software/dbus"
SECTION = "libs"
LICENSE = "GPLv2+ AFLv2.1"

DEPENDS = "expat glib-2.0 virtual/libintl dbus-glib-native dbus"
DEPENDS_virtclass-native = "expat-native glib-2.0-native dbus-native"

INC_PR = "r2"


SRC_URI = "\
           http://dbus.freedesktop.org/releases/dbus-glib/dbus-glib-${PV}.tar.gz \
           file://no-examples.patch \
           file://no-introspect.patch \
          "

SRC_URI_virtclass-native = "http://dbus.freedesktop.org/releases/dbus-glib/dbus-glib-${PV}.tar.gz"

inherit autotools pkgconfig gettext

BBCLASSEXTEND = "native"

do_configure_prepend() {
        install -m 0644 ${STAGING_DATADIR_NATIVE}/dbus/dbus-bus-introspect.xml ${S}/tools/
        install -m 0644 ${STAGING_DATADIR_NATIVE}/dbus/dbus-glib-bindings.h ${S}/tools/

}

do_configure_virtclass-native() {
        autotools_do_configure
}

do_install_virtclass-native() {
        autotools_do_install
        install -d ${D}${datadir}/dbus
        install -m 0644 tools/dbus-glib-bindings.h ${D}${datadir}/dbus
}

PACKAGES += "${PN}-bash-completion"

FILES_${PN}-bash-completion = "\
  ${sysconfdir}/bash_completion.d \
  ${prefix}/libexec/dbus-bash-completion-helper \
"

FILES_${PN} = "${libdir}/lib*.so.*"

FILES_${PN}-dev += "\
  ${libdir}/dbus-1.0/include \
  ${bindir}/dbus-glib-tool \
  ${bindir}/dbus-binding-tool \
"
