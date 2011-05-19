require ttf.inc

DESCRIPTION = "Ubuntu Font Family - TTF Version"
HOMEPAGE = "http://font.ubuntu.com"
LICENSE = "UFL"
PR = "r1"

SRC_URI = "http://font.ubuntu.com/download/ubuntu-font-family-${PV}+ufl.zip"
SRC_URI[md5sum] = "2c3287ea72f81ce025095f7a6d68af28"
SRC_URI[sha256sum] = "23206a306d92ec57404ab3bc75610b6d8cfed0b8ddcfbe51b99fb48519a4855b"
S = "${WORKDIR}/ubuntu-font-family-0.69+ufl"

PACKAGES = "${PN}-dbg ttf-ubuntu-mono ttf-ubuntu-sans ttf-ubuntu-serif"
RRECOMMENDS_${PN}-dbg = ""

FILES_ttf-ubuntu-mono  = "${datadir}/fonts/truetype/*Mono*"
FILES_ttf-ubuntu-sans  = "${datadir}/fonts/truetype/Ubuntu-*"
FILES_ttf-ubuntu-serif = "${datadir}/fonts/truetype/*Serif*"
