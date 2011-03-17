require cairo.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=e73e999e0c72b5ac9012424fa157ad77"

PR = "r1"

SRC_URI = "http://cairographics.org/releases/cairo-${PV}.tar.gz;name=cairo \
"

SRC_URI[cairo.md5sum] = "70a2ece66cf473d976e2db0f75bf199e"
SRC_URI[cairo.sha256sum] = "0f2ce4cc4615594088d74eb8b5360bad7c3cc3c3da9b61af9bfd979ed1ed94b2"

