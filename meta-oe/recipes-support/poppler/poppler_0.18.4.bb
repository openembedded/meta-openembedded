require poppler.inc

DEPENDS += "lcms"

EXTRA_OECONF_append = " --disable-abiword-output "

RDEPENDS_${PN} = "poppler-data"

SRC_URI[md5sum] = "12658f3308597e57f3faff538cc73baf"
SRC_URI[sha256sum] = "33421148cdc8e043da291dece2cce1cea6220d49a50c00c56d56d6435501d42e"
