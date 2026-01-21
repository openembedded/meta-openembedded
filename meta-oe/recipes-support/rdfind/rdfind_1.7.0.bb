SUMMARY = "Rdfind is a program that finds duplicate files"
HOMEPAGE = "https://rdfind.pauldreik.se/"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=fa22e16ebbe6638b2bd253338fbded9f"

DEPENDS = "nettle autoconf-archive"

SRC_URI = "https://rdfind.pauldreik.se/${BP}.tar.gz"
SRC_URI[sha256sum] = "78c463152e1d9e4fd1bfeb83b9c92df5e7fc4c5f93c7d426fb1f7efa2be4df29"

inherit autotools

BBCLASSEXTEND = "native"
