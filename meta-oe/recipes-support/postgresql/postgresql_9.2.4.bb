require postgresql.inc

LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=ab55a5887d3f8ba77d0fd7db787e4bab"

PR = "${INC_PR}.0"

SRC_URI += "\
    file://remove.autoconf.version.check.patch \
    file://ecpg-parallel-make-fix.patch \
"

SRC_URI[md5sum] = "6ee5bb53b97da7c6ad9cb0825d3300dd"
SRC_URI[sha256sum] = "d97dd918a88a4449225998f46aafa85216a3f89163a3411830d6890507ffae93"
