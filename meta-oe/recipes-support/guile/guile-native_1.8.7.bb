require guile-native.inc

LIC_FILES_CHKSUM = "file://LICENSE;md5=c9ba0d76ca3ef2a1d15a2ac839ef01fa"

PR = "r1"
SRC_URI = "http://ftp.gnu.org/pub/gnu/guile/guile-${PV}.tar.gz \
           file://configure-fix.patch \
           file://cpp-linemarkers.patch \
           file://reloc.patch \
          "

SRC_URI[md5sum] = "991b5b3efcbbc3f7507d05bc42f80a5e"
SRC_URI[sha256sum] = "bfee6339d91955a637e7f541d96f5b1d53271b42bb4a37b8867d186a6c66f0b3"
