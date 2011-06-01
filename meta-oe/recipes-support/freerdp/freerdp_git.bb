# Copyright (C) 2010, 2011, O.S. Systems Software Ltda.
# Released under the MIT license

include freerdp.inc

inherit gitpkgv

PV = "gitr${SRCPV}"
PKGV = "${GITPKGVTAG}"
PR = "${INC_PR}.2"

SRCREV = "dd759622a097474114ce425021ef52d0aa5d3957"
SRC_URI = "git://github.com/FreeRDP/FreeRDP.git;protocol=git"

S = "${WORKDIR}/git"
