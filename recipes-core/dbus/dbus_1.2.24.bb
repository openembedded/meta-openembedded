include dbus.inc

PR = "${INC_PR}.1"

SRC_URI = "\
  http://dbus.freedesktop.org/releases/dbus/dbus-${PV}.tar.gz;name=dbus \
  file://tmpdir.patch \
  file://fix-install-daemon.patch \
  file://0001-Make-the-default-DBus-reply-timeout-configurable.patch \
  file://dbus-1.init \
  file://reuseaddr01.patch \
  file://reuseaddr02.patch \
"

SRC_URI_append_hipox = "file://anon.patch"

SRC_URI[dbus.md5sum] = "565346cecd9cfecf1463540c6086cc2c"
SRC_URI[dbus.sha256sum] = "f12c748f4a703655e3d4c3db94cdf5a752a0cd0b36958c715804373bd3595c48"

