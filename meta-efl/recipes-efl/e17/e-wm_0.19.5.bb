require ${BPN}.inc

SRCNAME = "enlightenment"

S = "${WORKDIR}/${SRCNAME}-${PV}"

# couple of modules needed for illume2 (mobile) profile were removed in
# http://git.enlightenment.org/core/enlightenment.git/commit/src/modules/Makefile.mk?id=1be76d599ca27f820b58b8186c5f73d9844c67ca
# and replacements aren't included yet, if you want to use e-wm on device with small screen, better stay with 0.18 release
DEFAULT_PREFERENCE = "-1"

SRC_URI = "\
    ${E_RELEASES}/apps/${SRCNAME}/${SRCNAME}-${SRCVER}.tar.gz \
    file://enlightenment_start.oe \
    file://applications.menu \
"

SRC_URI[md5sum] = "f7557e89b914b4c66eacace66c739437"
SRC_URI[sha256sum] = "93d27d04e129f6ff751eeec8cda45bbf4f5da6787f8c3e85a4f1aecadc3f0372"
