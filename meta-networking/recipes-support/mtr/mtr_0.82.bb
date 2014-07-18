SUMMARY = "Combined traceroute and ping utility"
DESCRIPTION = "mtr combines the functionality of the 'traceroute' and 'ping' programs in a single network diagnostic tool."
HOMEPAGE = "http://www.bitwizard.nl/mtr/"
DEPENDS = "ncurses"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=0636e73ff0215e8d672dc4c32c317bb3 \
                    file://mtr.c;beginline=5;endline=16;md5=56e390ced194aff352eefab404883057"

SRC_URI = "ftp://ftp.bitwizard.nl/mtr/mtr-${PV}.tar.gz \
           file://configure.patch \
           file://no-gtk.patch"

SRC_URI[md5sum] = "10601ea543fda3e51545c4bce195b64c"
SRC_URI[sha256sum] = "f3b457c9623ae03565688a7ffd49d4843a5e2505ccaf3ba8d9fbd86e3ce9b6a0"

inherit autotools

EXTRA_OECONF = "--without-gtk"

