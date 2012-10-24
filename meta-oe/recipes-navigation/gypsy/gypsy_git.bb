require gypsy.inc

DEFAULT_PREFERENCE = "-1"

SRCREV = "be8c9c382d2d1d37b51d29b0843045121ec90213"
PV = "0.9+git${SRCPV}"
PR = "r1"

S = "${WORKDIR}/git"

LICENSE = "GPLv2+ & LGPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe \
                    file://COPYING.lib;md5=7fbc338309ac38fefcd64b04bb903e34 \
                    file://src/main.c;beginline=1;endline=25;md5=3fe64e27e61b289b77383a54a982cbdd \
                    file://gypsy/gypsy-time.h;beginline=1;endline=24;md5=06432ea19a7b6607428d04d9dadc37fd"

SRC_URI = "git://anongit.freedesktop.org/gypsy;protocol=git \
           file://fixups.patch"
