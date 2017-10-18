require postgresql.inc

LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=81b69ddb31a8be66baafd14a90146ee2"

SRC_URI += "\
    file://remove.autoconf.version.check.patch \
    file://not-check-libperl.patch \
"

do_compile_prepend_libc-musl() {
    sed -i -e 's/\-lnsl//g' ${B}/src/Makefile.global
}

SRC_URI[md5sum] = "2b7e2a2b0232063b68b3f8a0a01902bd"
SRC_URI[sha256sum] = "8e7df23a104b057b360d03180ebcb67f645e198a4a0bee94bf56b2bc9505ec6b"
