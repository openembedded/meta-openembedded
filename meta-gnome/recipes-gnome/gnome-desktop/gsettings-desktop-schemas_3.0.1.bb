LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

inherit gnome

RDEPENDS_${PN} = "glib-2.0-utils"

pkg_postinst_${PN} () {
if [ -n "$D" ]; then
    exit 1
fi

glib-compile-schemas ${datadir}/glib-2.0/schemas
}

SRC_URI[archive.md5sum] = "d42f0d79d6449a0eb1bb502eee8eb709"
SRC_URI[archive.sha256sum] = "6e72158a0e70ae50b8bb992e1486dd2fda43207f4ec4eb03a0d769f210363fd9"
