require ${BPN}.inc

SRCNAME = "enlightenment"

S = "${WORKDIR}/${SRCNAME}-${PV}"

# couple of modules needed for illume2 (mobile) profile were removed in
# http://git.enlightenment.org/core/enlightenment.git/commit/src/modules/Makefile.mk?id=1be76d599ca27f820b58b8186c5f73d9844c67ca
# and replacements aren't included yet, if you want to use e-wm on device with small screen, better stay with 0.18 release
DEFAULT_PREFERENCE = "-1"

SRC_URI = "\
    ${E_RELEASES}/apps/${SRCNAME}/${SRCNAME}-${SRCVER}.tar.gz \
    file://0001-configure.ac-add-foreign.patch \
    file://enlightenment_start.oe \
    file://applications.menu \
"

SRC_URI[md5sum] = "2c5a110496a867253f31cf7ed1a2143a"
SRC_URI[sha256sum] = "3430d94c3def706de3b421ee48d92f2a475224f2f86111442a5d2d90c2363116"
