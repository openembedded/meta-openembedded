require clutter-box2d.inc

LIC_FILES_CHKSUM = "file://box2d/License.txt;md5=e5d39ad91f7dc4692dcdb1d85139ec6b"

SRCREV = "de5452e56b537a11fd7f9453d048ff4b4793b5a2"
PV = "0.12.1+git${SRCPV}"
PR = "r1"

SRC_URI = "git://git.gnome.org/clutter-box2d.git"

S = "${WORKDIR}/git"

DEPENDS += "clutter-1.8"
PROVIDES = "clutter-box2d-1.6"
