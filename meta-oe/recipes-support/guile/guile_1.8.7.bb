require guile.inc

LIC_FILES_CHKSUM = "file://LICENSE;md5=5711ae313ffd140741e741b88d9d4007"

PR = "r1"

SRC_URI = "http://ftp.gnu.org/pub/gnu/guile/guile-${PV}.tar.gz \
           file://configure-fix.patch \
           file://18.diff \
          "


SRC_URI[md5sum] = "991b5b3efcbbc3f7507d05bc42f80a5e"
SRC_URI[sha256sum] = "bfee6339d91955a637e7f541d96f5b1d53271b42bb4a37b8867d186a6c66f0b3"
