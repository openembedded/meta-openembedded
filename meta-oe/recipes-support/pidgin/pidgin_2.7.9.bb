require pidgin.inc
PR = "${INC_PR}.1"

DEPENDS += "farsight2 libidn"

SRC_URI = "\
  ${SOURCEFORGE_MIRROR}/pidgin/pidgin-${PV}.tar.bz2 \
  file://sanitize-configure.ac.patch \
  file://pidgin.desktop-set-icon.patch \
  file://purple-OE-branding-25.patch \
  file://pidgin-cross-python-265.patch \
"

SRC_URI[md5sum] = "9bc6cf953ed7d383b215fa8487bf8829"
SRC_URI[sha256sum] = "9722d7f199a6704e29900c80f270d9409d5c28caab77f495b68108d81ba3e19e"

EXTRA_OECONF += "\
  --disable-gtkspell \
  --disable-meanwhile \
  --disable-nm \
  --disable-screensaver \
"
