require postgresql.inc

LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=7d847a9b446ddfe187acfac664189672"

PR = "${INC_PR}.0"

SRC_URI += "\
    file://remove.autoconf.version.check.patch \
    file://not-check-libperl.patch \
"

SRC_URI[md5sum] = "b6369156607a4fd88f21af6fec0f30b9"
SRC_URI[sha256sum] = "81fda191c165ba1d25d75cd0166ece5abdcb4a7f5eca01b349371e279ebb4d11"
