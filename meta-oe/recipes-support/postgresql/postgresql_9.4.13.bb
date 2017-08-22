require postgresql.inc

LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=81b69ddb31a8be66baafd14a90146ee2"

SRC_URI += "\
    file://remove.autoconf.version.check.patch \
    file://not-check-libperl.patch \
"

do_compile_prepend_libc-musl() {
    sed -i -e 's/\-lnsl//g' ${B}/src/Makefile.global
}

SRC_URI[md5sum] = "dbdb9fbe1b9a394b9ac19d3113b73944"
SRC_URI[sha256sum] = "0080f55d65194de8b96a2dab153443f8248ff2b2b10e6ab4cda2dcadcac7f2ab"
