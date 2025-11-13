# Copyright (C) 2022 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "LibSpiro is an adaptation of Spiro formula and functions into a sharable library"
HOMEPAGE = "https://github.com/fontforge/libspiro"
LICENSE = "GPL-3.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

SRC_URI = "https://github.com/fontforge/libspiro/releases/download/${PV}/libspiro-dist-${PV}.tar.gz"
SRC_URI[sha256sum] = "c573228542b3bf78a51c5ce7b0a609e8a2763938a088376230c452c4f9714848"

inherit autotools

BBCLASSEXTEND = "native"
