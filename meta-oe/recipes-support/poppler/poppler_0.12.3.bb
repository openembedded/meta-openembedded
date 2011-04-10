require poppler.inc

DEPENDS += "lcms"

EXTRA_OECONF_append = " --disable-abiword-output "

RDEPENDS_${PN} = "poppler-data"

SRC_URI[md5sum] = "d0ca8344d8d94e27aaba6d432688365d"
SRC_URI[sha256sum] = "7a4ffe6d2950c446c285700d3b2dc399540a27ce635dd712aff646f02f8dfbcc"
