# Copyright (C) 2010, 2011, O.S. Systems Software Ltda.
# Released under the MIT license

include freerdp.inc

inherit gitpkgv

PV = "gitr${SRCPV}"
PKGV = "${GITPKGVTAG}"
PR = "${INC_PR}.3"

SRCREV = "b3eb650c919c4b3b1e5bc12cea6afb05282a7232"
SRC_URI = "git://github.com/FreeRDP/FreeRDP-old.git;protocol=git"

S = "${WORKDIR}/git"
