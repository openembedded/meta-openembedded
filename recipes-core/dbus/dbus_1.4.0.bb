include dbus.inc
PR = "${INC_PR}.0"

BBCLASSEXTEND = "native"

SRC_URI = "\
  http://dbus.freedesktop.org/releases/dbus/dbus-${PV}.tar.gz \
  \
  file://tmpdir.patch \
  file://add-configurable-reply-timeouts.patch \
  file://dbus-1.init \
"            

# This needs more testing before pushing as default dbus
DEFAULT_PREFERENCE = "-1"

SRC_URI[md5sum] = "f59618b18d2fb2bd1fce9e1c5a2a3282"
SRC_URI[sha256sum] = "c96d2f86d40e158e2bf405925a0a42cce0533a8466098e2f2238aa1614926652"
