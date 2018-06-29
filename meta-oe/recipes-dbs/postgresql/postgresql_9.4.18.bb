require postgresql.inc

LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=6dc95e63aa4d72502ff8193dfe2ddd38"

SRC_URI += "\
    file://not-check-libperl.patch \
"

UPSTREAM_CHECK_URI = "http://ftp.postgresql.org/pub/source/"
UPSTREAM_CHECK_REGEX = "(?P<pver>v9.4(\.\d+)+(-P\d+)*)/"

SRC_URI[md5sum] = "4032f32af5afb0aa8f78f9a5639c0873"
SRC_URI[sha256sum] = "428337f2b2f5e3ea21b8a44f88eb89c99a07a324559b99aebe777c9abdf4c4c0"
