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

SRC_URI[md5sum] = "001b657528aadafcfae5b89b2c81aee8"
SRC_URI[sha256sum] = "9d2e3c9e25dd48a8f5948d9c7757863d40f7b5bdbc13fdca5a72fb050e048dec"
