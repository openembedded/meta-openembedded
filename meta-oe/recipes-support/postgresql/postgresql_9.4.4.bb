require postgresql.inc

LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=7d847a9b446ddfe187acfac664189672"

PR = "${INC_PR}.0"

SRC_URI += "\
    file://remove.autoconf.version.check.patch \
    file://not-check-libperl.patch \
"

SRC_URI[md5sum] = "1fe952c44ed26d7e6a335cf991a9c1c6"
SRC_URI[sha256sum] = "538ed99688d6fdbec6fd166d1779cf4588bf2f16c52304e5ef29f904c43b0013"

