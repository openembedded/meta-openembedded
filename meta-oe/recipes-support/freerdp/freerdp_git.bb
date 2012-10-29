# Copyright (C) 2010-2012, O.S. Systems Software Ltda.
# Released under the MIT license

include freerdp.inc

inherit gitpkgv

PV = "gitr${SRCPV}"
PKGV = "${GITPKGVTAG}"
PR = "${INC_PR}.0"

SRCREV = "19d4a4276869ec40ee1eef066ac460615734c043"
SRC_URI = "git://github.com/FreeRDP/FreeRDP.git"

S = "${WORKDIR}/git"
