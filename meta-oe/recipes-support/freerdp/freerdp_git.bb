# Copyright (C) 2010, 2011, O.S. Systems Software Ltda.
# Released under the MIT license

include freerdp.inc

inherit gitpkgv

PV = "gitr${SRCPV}"
PKGV = "${GITPKGVTAG}"
PR = "${INC_PR}.2"

SRCREV = "b05747b55ccea8d2f2b830f89d3c0eb622d66784"
SRC_URI = "git://github.com/FreeRDP/FreeRDP.git;protocol=git"

S = "${WORKDIR}/git"
