DESCRIPTION = "Tools for Flash-Friendly File System (F2FS)"
HOMEPAGE = "http://sourceforge.net/projects/f2fs-tools/"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=15667d9b3cb737b57471c148b7c50734"

SRC_URI = "${SOURCEFORGE_MIRROR}/project/f2fs-tools/f2fs-tools-${PV}.tar.gz"
SRC_URI[md5sum] = "cf3608ea7475c8f9a03159d7f22e4d07"
SRC_URI[sha256sum] = "afd774b80f73721f2c4300c0b4780a24b705b8328df229f3ae37315c8bfbff1d"

inherit autotools

BBCLASSEXTEND = "native"

do_configure_prepend() {
	# workaround for endless do_configure loop:
	# make: Warning: File `Makefile.am' has modification time 5.3e+04 s in the future
	touch ${S}/*
}
