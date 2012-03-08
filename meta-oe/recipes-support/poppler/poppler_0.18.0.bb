require poppler.inc

PR = "r3"

DEPENDS += "lcms"

EXTRA_OECONF_append = " --disable-abiword-output "

RDEPENDS_${PN} = "poppler-data"

SRC_URI[md5sum] = "4cd3bf2a0a13fa8eaf00d31368915f77"
SRC_URI[sha256sum] = "9019b15ef10a878e607e4088a27fb4967f61cdfada7806d0fee97954f76648e8"
