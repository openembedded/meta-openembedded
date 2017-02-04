require postgresql.inc

LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=3a9c1120056a102a8c8c4013cd828dce"

SRC_URI += "\
    file://remove.autoconf.version.check.patch \
    file://not-check-libperl.patch \
"

SRC_URI[md5sum] = "1171df0426fe4da5a29f6cdaf2e8b812"
SRC_URI[sha256sum] = "7061678bed1981c681ce54c76b98b6ec17743f090a9775104a45e7e1a8826ecf"
