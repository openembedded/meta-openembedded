SUMMARY = "demonstration UI for dleyna"
HOMEPAGE = "https://01.org/dleyna/"

LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c \
                    file://src/media-service-demo.py;beginline=3;endline=21;md5=2faa3862e32422b3c38a1501539aa8b4"

SRCREV = "f4440e104f0d13e454234184c2c6eba982725baf"
PV = "0.0+git${SRCPV}"

SRC_URI = "git://github.com/01org/media-service-demo.git"

S = "${WORKDIR}/git"

inherit distutils

RDEPENDS_${PN} = "media-service-upnp python-dbus python-gst python-pygtk \
                  python-crypt python-netclient python-dateutil"
