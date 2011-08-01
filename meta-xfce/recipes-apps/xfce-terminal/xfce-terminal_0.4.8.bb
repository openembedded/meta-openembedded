DESCRIPTION = "Terminal emulator for the Xfce desktop environment"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=9ac2e7cff1ddaf48b6eab6028f23ef88"
DEPENDS = "virtual/libx11 exo gtk+ vte dbus-glib"

PR = "r0"

inherit xfce

# SRC_URI must follow inherited one
SRC_URI = "http://archive.xfce.org/src/apps/terminal//${@'${PV}'[0:3]}/Terminal-${PV}.tar.bz2 \
"

S = "${WORKDIR}/Terminal-${PV}"

FILES_${PN} += "${datadir}/Terminal \
                ${datadir}/gnome-control-center/default-apps/Terminal-default-apps.xml"

SRC_URI[md5sum] = "8dff62234da14e1a5e542fb56560ebe2"
SRC_URI[sha256sum] = "fe5db0b7127ce87efa1e10f44d8915be88005b6ce4f0bfea6b64533e25cc788d"
