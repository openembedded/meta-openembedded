SUMMARY = "A library for Microsoft compression formats"
DESCRIPTION = "The library provides compressors and decompressors,\
archivers and dearchivers for Microsoft compression formats: CAB, CHM, WIM,\
LIT, HLP, KWAJ and SZDD."
HOMEPAGE = "http://www.cabextract.org.uk/libmspack/"
SECTION = "lib"
LICENSE = "LGPL-2.1-only"

LIC_FILES_CHKSUM = "file://COPYING.LIB;md5=7fbc338309ac38fefcd64b04bb903e34"

SRC_URI = "https://www.cabextract.org.uk/libmspack/libmspack-${PV}.tar.gz"
SRC_URI[sha256sum] = "1834d1079a6e8db9546fa95d3a1fc2cf676124a8a60bbdd7f30b2db4eb4ad683"

inherit autotools

BBCLASSEXTEND += "native nativesdk"
