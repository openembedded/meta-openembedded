require dbus-glib.inc

PR = "${INC_PR}.1"

SRC_URI_virtclass-native += "file://run-with-tmp-session-bus.patch"

do_install_virtclass-native_prepend() {
        install -d ${D}${datadir}/dbus
        install -m 0644 dbus-bus-introspect.xml ${D}${datadir}/dbus
}

SRC_URI[md5sum] = "cd0ab148fb0c786fc88be49d19971f50"
SRC_URI[sha256sum] = "5351a6e7f38ffc641c34b4a4cdd9bed1c1dc7043a501096bac00a2876ea90bdc"

