# Copyright (C) 2010-2012, O.S. Systems Software Ltda.
# Released under the MIT license

include freerdp.inc

inherit gitpkgv

PV = "gitr${SRCPV}"
PKGV = "${GITPKGVTAG}"
PR = "${INC_PR}.0"

SRCREV = "e0f2b3ccc986a165d9c289a569b60eab66153792"
SRC_URI = "git://github.com/FreeRDP/FreeRDP.git"

S = "${WORKDIR}/git"
