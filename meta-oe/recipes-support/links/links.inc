DESCRIPTION = "Links is graphics and text mode WWW \
browser, similar to Lynx."
HOMEPAGE = "http://links.twibright.com/"
SECTION = "console/network"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b0c80473f97008e42e29a9f80fcc55ff"
DEPENDS = "jpeg libpng flex openssl zlib"

LPV = "${@d.getVar("PV",1).split("+")[1]}"

SRC_URI = "http://links.twibright.com/download/links-${LPV}.tar.bz2 \
           file://ac-prog-cxx.patch \
           file://cookies-save-0.96.patch \
           file://links-2.1pre17-fix-segfault-on-loading-cookies.patch"

inherit autotools

S = "${WORKDIR}/links-${LPV}"

