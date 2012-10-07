SUMMARY = "A simple presentation tool for hackers"
DESCRIPTION = "Pinpoint is a simple presentation tool that hopes to avoid audience death \
               by bullet point and instead encourage presentations containing beautiful \
               images and small amounts of concise text in slides."
SECTION = "x11/multimedia"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=fbc093901857fcd118f065f900982c24 \
                    file://pinpoint.c;beginline=6;endline=17;md5=201d438283607da393fae6aca085454c"

DEPENDS = "gnome-common glib-2.0 gdk-pixbuf clutter-1.8 clutter-gst-1.8"

inherit autotools gettext

SRC_URI = "git://git.gnome.org/browse/pinpoint;protocol=http"

SRCREV = "5b22660f5b247023ca409403f1c2cc834d8ce632"
PV = "0.1.4+gitr${SRCPV}"

S = "${WORKDIR}/git"

