require postgresql.inc

LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=29b81b066680be9ffd98cb9d2afb9de6"

PR = "${INC_PR}.0"

SRC_URI += "file://remove.autoconf.version.check.patch"

SRC_URI[md5sum] = "689397187bb1dfe9b5cbde99538311c3"
SRC_URI[sha256sum] = "08032849da121e67e318f7ff4e68d3ac88f29050e242641f717c4270839b597b"
